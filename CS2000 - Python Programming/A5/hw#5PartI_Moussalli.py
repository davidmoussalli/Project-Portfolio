# CS2000 
# Assignment 5 Part 1
# David Moussalli
# 3/29/2018
# 

import unittest
from functools import reduce
from audioop import reverse
#========================== Bookshop =======================================
class Bookshop():
    orders = [ 
               [1, ("5464", 4, 9.99), ("8274",18,12.99), ("9744", 9, 44.95)],
               [2, ("5464", 9, 9.99), ("9744", 9, 44.95)],
               [3, ("5464", 9, 9.99), ("88112", 11, 24.99)],
               [4, ("8732", 7, 11.99), ("7733", 11,18.99), ("88112", 5, 39.95)] 
             ]

    def test1(self):       
        invoice_totals = list( map(lambda x: [x[0]] +  list(map(lambda y: (y[0], y[1]*y[2]) if y[1]*y[2] >= 100 else (y[0], y[1]*y[2]+10), x[1:])), self.orders) )
        print('1. orders = [')
        for i in invoice_totals:
            print("\t     ",i)
        print('\t    ]', "\n")
        return invoice_totals


    def test2(self):
        invoice_totals = list( map(lambda x: (x[0], max(list(map(lambda tuple: 
                                                           tuple[0] if tuple[1]*tuple[2]  == min(list(map(lambda z: z[1]*z[2],  x[1:])))
                                                                    else '', x[1:])))   ) , self.orders) )

        print("2. ",invoice_totals, "\n")
        return(invoice_totals)
    
    def test3(self):
        invoice_totals = list( map(lambda x: (x[0], max(list(map(lambda tuple: 
                                                           tuple[0] if tuple[1]*tuple[2]  == max(list(map(lambda z: z[1]*z[2],  x[1:])))
                                                                    else '', x[1:])))   ) , self.orders) )

        print("3. ",invoice_totals, "\n")
        return(invoice_totals)
    
    def test4(self):
        invoice_totals = list( map(lambda x: (x[0], round(sum(list(map(lambda tuple: #Take sum of all total order amount 
                                                           tuple[1]*tuple[2] , x[1:]))), 2) #Round to 2 decimals
                                                                 ) , self.orders) )

        print("4. ",invoice_totals, "\n")
        return(invoice_totals)    
    
    def test5(self):
        invoice_totals = list( map(lambda x: x###(x[0], max(list(map(lambda tuple: tuple[0] if tuple[1]*tuple[2]  == max(list(map(lambda z: z[1]*z[2],  x[1:])))  else '', x[1:]
                                             #                  )))  
                                             #)
                                             ###
                                                                  , self.orders) )

        print("5. ",invoice_totals, "\n")
        return(invoice_totals)

#     def test6(self):
#         pass


#========================= TestBookshop ====================================
class TestBookshop(unittest.TestCase):
    def test1(self):
        actual = bs.test1()
        expected = [ [1,("5464",49.96),("8274",233.82),("9744",404.55)],
                     [2,("5464",99.91),("9744",404.55)],
                     [3,("5464",99.91),("88112",274.89)],
                     [4,("8732",93.93),("7733",208.89),("88112",199.75)]]
        
        self.assertEqual(actual, expected)
#     
    def test2(self):
        actual = bs.test2()
        expected =[(1,"5464"), (2,"5464"), (3,"5464"), (4,"8732")]
        self.assertEqual(actual, expected)
#    
    def test3(self):
        actual = bs.test3()
        expected = [(1,"9744"), (2,"9744"), (3,"88112"), (4,"7733")]
        self.assertEqual(actual, expected)
#         
    def test4(self):
        actual = bs.test4()
        expected = [(1, 678.33), (2, 494.46), (3, 364.8), (4, 492.57)]
        self.assertEqual(actual, expected)
#         
    def test5(self):
        actual = bs.test5()
        expected = ["9744", 809.1]
#         self.assertEqual(actual, expected)
     
#========================== MAIN ===========================================
def main():
    testbs = TestBookshop()

   
    
if __name__ == "__main__":
    bs = Bookshop()
    unittest.main()    


