
# Name: David Moussalli
# Date: 2/8/2018
# Homework: 3
#
# The trickiest part of this program for me was converting the hex number to binary, since 
#	we couldn't use the bin() method. Everywhere I looked online, people just said to use bin() >.<
#	but I eventually figured it out when I was just looking up how to get the binary equivalent
#	of a number on paper. I found that by first converting the number to a decimal, I could then 
#	store the result of "number % 2" to the start of a string, then dividing the number by 2 (until it was 0),
#	the string would turn out to be the binary equivalent of the binary number. 
#
# SEE BOTTOM FOR OUTPUT.
#
##########################################################################################################
######################################### References: ####################################################
##########################################################################################################
#
# Reference 1:
# 	Title: "Python Verifying if input is int and greater than 0"
# 	Asked by: "Nestor Cristian"
# 	Answered by: "tdelaney"
# 	Date asked: Oct 13 2015 at 20:50
# 	Date answered: Oct 13 2015 at 21:03
# 	Date used: Feb 13, 2018
# 	Availability:
# 		- https://stackoverflow.com/questions/33112377/python-verifying-if-input-is-int-and-greater-than-0




#The following three methods perform operations on hex integers sent from hex_operation()
#	and return the answer...They are called until all integers have been operated on...
def _or(intList): #OR operation method
	result = int(intList[0], 16)
	for i in range(1, len(intList)):
		result = int(hex(result | int(intList[i], 16)), 16)
	return hex(result)

def _and(intList): #AND operation method
	result = int(intList[0], 16)
	for i in range(1, len(intList)):
		result = int(hex(result & int(intList[i], 16)), 16)
	return hex(result)

def _xor(intList): #XOR operation method
	result = int(intList[0], 16)
	for i in range(1, len(intList)):
		result = int(hex(result ^ int(intList[i], 16)), 16)
	return hex(result)

#================================================================================================
#The following three methods perform operations on binary integers sent from hex_binary()
#	and return the answer...They are called until all integers have been operated on...
def bin_or(intList): #OR operation method
	if intList[0] == '':
		intList[0] = '0'
	result = int(intList[0], 2)

	for i in range(1, len(intList)):
		result = result | int(intList[i], 2)
	return result

def bin_and(intList): #AND operation method
	if intList[0] == '':
		result = 0
	else:
		result = int(intList[0], 2)

	for i in range(1, len(intList)):
		result = result & int(intList[i], 2)
	return result

def bin_xor(intList): #XOR operation method
	if intList[0] == '':
		result = 0
	else:
		result = int(intList[0], 2)

	for i in range(1, len(intList)):
		result = result ^ int(intList[i], 2)
	return result


#This method asks the user for the number of integers...
def get_numInts(): 
	numInts = -1
	while True:
		try: #See reference 1
			 #We use a try statement here to see if the input is an integer...If it isn't, display a message and ask again.
			numInts = int(input('Enter number of integers: '))
			if numInts > 1: #If the number is an int greater than 1, break out of the loop.
				break 
			else:
				print('The integer must be greater than 1...')
		except ValueError:
			print('Value must be an integer...')
	return numInts

#This method asks the user for integers, then calls check_hex() to check if they are in hex format...
def get_integers(numInts):
	intList = []
	for i in range(0, numInts):
		passedBool = False
		while passedBool == False:
			print('Enter integer', i+1, end=': ')
			int1 = input()
			passedBool = check_hex(int1)
			if passedBool == True:
				intList.append(int1)
	return intList

#This method checks that the integers are in the right format (<=8 characters and in hex format)...
def check_hex(num):
	hexDigits = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'] #List of hex digits
	passedHexTest = False
	if(len(num) <= 8): #Check length.
		for char in num: #Check each character
			if char in hexDigits:
				passedHexTest = True
			else: #If any char is not a hex digit, break the loop and ask for another number.
				print('Please enter an 8-digit hexadecimal integer')
				passedHexTest = False
				return passedHexTest
	else:
		print('Please enter an 8-digit hexadecimal integer')
	return passedHexTest

#This method performs the operation on the hex integers...
def hex_operation(operation, intList):
	print('\nHexadecimal operation:\n', end = '   ')
	for i in range(0, len(intList)):
	 	print('%08X' % int(intList[i], 16)) #Print the number in caps, with any necessary leading 0's 
	 	if(i < len(intList)-1):
	 		print(operation, end='  ')

	#Calculate hexAnswer
	if operation == '|':
		hexAnswer = _or(intList)
	elif operation == '&':
		hexAnswer = _and(intList)
	else:
		hexAnswer = _xor(intList)
	hexAnswer = hexAnswer[2:] #Get rid of '0x' at the start of the hex number

	print('=  %08X\n' % int(hexAnswer, 16), end='') #Print the answer in caps, with any necessary leading 0's 
	return operation, intList, hexAnswer

#This method converts the hex integers to binary, and performs the operation on the binary integers...
def hex_binary(operation, intList, hexAnswer):
	binAnswer='' #Set the binary answer string to ''

	for i in range(0, len(intList)):
		binaryStr = ''
		intList[i] = int(intList[i], 16) #Convert hex to decimal 

		#This next statement might seem weird, but for some reason, if the binary number was 0,
		#	it would not print out nothing, so I had to add '0' to the string if that was the case...
		if(intList[i] == 0):
			intList[i] = '0'
		else:
			while(intList[i] != 0): #Find the binary equivalent of the integer 
				binaryStr = str(intList[i] % 2) + binaryStr
				intList[i] = (intList[i] // 2)

			intList[i] = binaryStr

	print('\nBinary operation:\n', end = ' ')

	#Print out all the integers...
	for i in range(0, len(intList)):
		#...Adding any necessary leading 0's...
		while len(intList[i]) < 32:
			intList[i] = ('0'  + intList[i])
		#...with the correct spacing (every 8 characters)
		for j in range(0, len(intList[i])):
			if(j%8 == 0):
				print(' ',intList[i][j], end='')
			else:
				print(intList[i][j], end='')
		print() #Extra line
		if(i < len(intList)-1):
			print(operation, end='')


	#Test operation and call the appropriate method.
	if operation == '|':
		binAnswer = bin_or(intList)
	elif operation == '&':
		binAnswer = bin_and(intList)
	else:
		binAnswer = bin_xor(intList)

	#Convert answer to binary:
	binaryStr = ''
	while(binAnswer != 0): #Find the binary equivalent of the answer integer
		binaryStr = str(binAnswer % 2) + binaryStr
		binAnswer = (binAnswer // 2)

	#Add any necessary leading 0's
	while len(binaryStr) < 32:
			binaryStr = ('0' + binaryStr)
	print('=', end='')

	#Print the binary Answer string in the appropriate format.
	for j in range(0, len(binaryStr)):
			if(j%8 == 0):
				print(' ',binaryStr[j], end='')
			else:
				print(binaryStr[j], end='')
	print()#Extra line


#Start of main ==========================================================================
possibleOperations = ['|', '&', '^', 'q'] #Holds the possible operations
operation = '' #Holds the operation given by the user
intList = [] #Create empty lit to hold hex numbers

while True:
	operation = input('\nEnter operation: ')
	if operation == 'q': #if q is entered, quit the program
		break
	if operation in possibleOperations:
		#Call to get_numInts()
		numInts = get_numInts() #Pass the operation to get_numInts()

		#Call to get_integers()...Which calls check_hex()
		intList = get_integers(numInts)

		#Call to hex_operation()
		operation, intList, hexAnswer = hex_operation(operation, intList)

		#Call to hex_binary()
		hex_binary(operation, intList, hexAnswer)

	else: #If the operation entered by the user is not in the list of possible operations, ask again.
		print('Please enter |, &, ^, or q')



################################################################################################
######################################### OUTPUT ###############################################
################################################################################################



#Example 1:

# Enter operation: |
# Enter number of integers: 2
# Enter integer 1: 000000FF
# Enter integer 2: 30C01101

# Hexadecimal operation:
#    000000FF
# |  30C01101
# =  30C011FF

# Binary operation:
#    00000000  00000000  00000000  11111111
# |  00110000  11000000  00010001  00000001
# =  00110000  11000000  00010001  11111111

# Enter operation: q




# #Example 2:

# Enter operation: @
# Please enter |, &, ^, or q

# Enter operation: &
# Enter number of integers: 3
# Enter integer 1: DF5383
# Enter integer 2: Z0238984
# Please enter an 8-digit hexadecimal integer
# Enter integer 2: 908070605040
# Please enter an 8-digit hexadecimal integer
# Enter integer 2: C5204
# Enter integer 3: F13FB

# Hexadecimal operation:
#    00DF5383
# &  000C5204
# &  000F13FB
# =  000C1200

# Binary operation:
#    00000000  11011111  01010011  10000011
# &  00000000  00001100  01010010  00000100
# &  00000000  00001111  00010011  11111011
# =  00000000  00001100  00010010  00000000

# Enter operation: q
