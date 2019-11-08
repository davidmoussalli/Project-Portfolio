#include <msp430.h>
#include <libemb/serial/serial.h>              
#include <libemb/conio/conio.h> 
#include <string.h>
#include "colors.h"

void checkLetter(int number);    //Method that checks which hex digit to show on the 7-segment display

int NUM = 1;    //Global int used to determine which color is being adjusted with the potentiometer.

int RED = 0;        //Brightness of red on the RGB
int GREEN = 0;        //Brightness of green on the RGB
int BLUE = 0;        //Brightness of blue on the RGB

int DIGIT = 1;
int sixteens = 0x0;    //Left digit on the 7-segment display
int ones = 0x0;        //Right digit on the 7-segment display

int A = BIT0;         //P1.0
int B = BIT5;         //P1.5
int c = BIT0;         //P2.0
int D = BIT2;         //P2.2    
int E = BIT3;         //P2.3
int F = BIT4;         //P2.4    
int G = BIT7;         //P1.7    
int XIN = BIT6;       //P2.6
int XOUT = BIT7;      //P2.7

int main(void)
{
    WDTCTL  = WDTPW | WDTHOLD;             // Hold the watch dog timer
    BCSCTL1 = CALBC1_1MHZ;           // Calibrate the microcontroller to 1MHZ
    DCOCTL  = CALDCO_1MHZ;

/************ Port 1 stuff ****************************************************************/

    P1DIR = A|B|BIT6|BIT7;            // Put bits into alternate output
    P1SEL = BIT6|BIT4;            // BIT4 analog input (channel 4) - They are no longer GPIO pins, and now belong to the timer

    TA0CTL   = TASSEL_2 | MC_1 | ID_3;      // use TA0.1 for PWM on P1.6
    TA0CCR0  = 0x3FF;                       // 10-bit maximum value
    TA0CCR1  = 0;//Red 

    TA0CCTL0 = CCIE;                      // interrupt enabled
    TA0CCTL1 = OUTMOD_3;                    // reset/set output mode

/************ Port 2 stuff ****************************************************************/

    P2DIR = -1;//Set all P2 pins as output. pin2.1 and pin2.5(Blue and Green) and 7-seg pins
    P2SEL = BIT1|BIT5; 
    P2OUT = XIN|XOUT;

    TA1CTL   = TASSEL_2 | MC_1 | ID_3;      // use TA0.1 for PWM on P1.6
    TA1CCR0  = 0x3FF;                       // 10-bit maximum value
    TA1CCR1  = 0;//Blue       
    TA1CCR2  = 0;//Green

    TA1CCTL1 = OUTMOD_3;                    // reset/set output mode
    TA1CCTL2 = OUTMOD_3;

/************************ Potentiometer ***************************************************/
    ADC10CTL1 = INCH_4 | ADC10DIV_3;        // ADC10 channel 4, clock divider 3
    ADC10CTL0 = SREF_0 | ADC10SHT_3 |       // VCC/VSS ref, 64 x ADC10CLKs
               ADC10ON | ADC10IE;           // ADC10 enable, ADC10 interrupt enable%
    ADC10AE0  = BIT4;                       // analog enable channel 4

/*********************** Button stuff *****************************************************/
    P1REN     = BIT3;  //Enabling the pullup resistor for the switch
    P1OUT  = BIT3;     //Set the switch's initial state to high
    P1IE   |= BIT3;     //Enable the interupt on p1.3
    P1IES  |= BIT3;  //Interupt edge select
    P1IFG  &= ~BIT3; //Clear the interupt flag for bit 3 on that port.

    serial_init(9600);
    __enable_interrupt();                // interrupts enabled  
    cio_printf((char *) "\n\rWorking on red...\n\r");
    while(1)
    {
        __delay_cycles(10000);             // wait for ADC ref to settle
        ADC10CTL0 |= ENC + ADC10SC;        // sampling and conversion start
    if(NUM == 1){
        RED = ((ADC10MEM & 0x3F8)/128);
            TA0CCR1 = ADC10MEM & 0x3F8; // assigns the value held in ADC10MEM to the TA0CCR1 register
        }
    else if(NUM == 2){
        GREEN = ((ADC10MEM & 0x3F8)/128);
        TA1CCR2 = ADC10MEM & 0x3F8;    // assigns the value held in ADC10MEM to the TA1CCR2 register 
    }
    else if(NUM == 3){
        BLUE = ((ADC10MEM & 0x3F8)/128);
        TA1CCR1 = ADC10MEM & 0x3F8; // assigns the value held in ADC10MEM to the TA1CCR1 register
        }

        int colorNum = (RED + (GREEN * 8) + (BLUE * 64));
        if(strcmp(colors[colorNum],"Teal"))
        {
            cio_printf("\rRed %i, Green %i, Blue %i, Color: %s             ",
                            RED, GREEN, BLUE, colors[colorNum]);
        }
        else{
        cio_printf("\n\rYou've got teal!");
        }
    }
    return 0;
}

/************************ On-board Switch Interrupt ***********************************************/
// PORT1 interrupt service routine
#pragma vector = PORT1_VECTOR
__interrupt void Port_1(void)
{
    if(NUM == 1){
        NUM = 2;
    cio_printf((char *) "\n\rWorking on green...\n\r");
        }
    else if(NUM == 2){
        NUM = 3;
    cio_printf((char *) "\n\rWorking on blue...\n\r");
    }
    else if(NUM == 3){
        NUM = 1;
    cio_printf((char *) "\n\rWorking on red...\n\r");
    }
   while(!(BIT3 & P1IN)) {}  //Debounce button
   __delay_cycles(32000);
   P1IFG &= ~BIT3;    //Clear the interrupt flag
}


/************************ 7-Segment Display ***********************************************/
// 7-seg interrupt service routine
#pragma vector=TIMER0_A0_VECTOR
__interrupt void Timer_A (void)
{
    int number = ((ADC10MEM & 0x3F8)/8);

//Now convert to hex
    ones = (number%16);
    sixteens = ((number/16)%16);

    if(DIGIT == 1)//Then handle digit 1
    {
        P2OUT = XIN;
        P1OUT = BIT3;
    checkLetter(sixteens);
        DIGIT = 2;
    }
    else if(DIGIT == 2)
    {
        P2OUT = XOUT;
        P1OUT = BIT3;
    checkLetter(ones);
        DIGIT = 1;
    }
}
