# Name: David Moussalli
# Date: 4/13/2018
# Homework: 6
# 
#===============================================================================
# References:
#===============================================================================
# Reference 1: Reading CSV Files
#     Title: "12.1 Reading files"
#     Publisher: zyBooks
#     Date used: 4/10/2018
#     Availability:
#         - https://learn.zybooks.com/zybook/WMICHCS2000ALmaweeSpring2018/chapter/12/section/1
#
# Reference 2: Iterators and Generators
#     Title:   "Python3 Intermediate Tutorial 5 - Iterators & Generators"
#     Channel: "DrapsTV"
#     Published: Nov 6, 2014
#     Date used: April 10, 2018
#     Availability:
#          - https://www.youtube.com/watch?v=BC77x_GLmxo&ab_channel=DrapsTV
#
# Reference 3: Generators
#     Title:   "Python 201: An Intro to Generators"
#     Author: Mike L. Driscoll
#     Published: January 27, 2014
#     Date used: April 10, 2018
#     Availability:
#          - https://www.blog.pythonlibrary.org/2014/01/27/python-201-an-intro-to-generators/


#===============================================================================
# CSVOpen - See References 2 and 3
#===============================================================================
def csvopen(filename):
    try:
        file = open(filename)   #See Reference 1
        line = next(file)       #Skip header
        
        rownum = 0
        while True:
            rownum += 1
            line = next(file)
            attributes = line.split(',')
            first, last, age = attributes[0], attributes[1][1:], int(attributes[2]) #Delete leading space for each last name.
            if(age%2): #If the age is odd
                print('{0}: {1}, {2}'.format(rownum, last, first))
            else:
                print('{0}{1} is number {2}'.format(first[0], last[0], rownum))
        #End while
        file.close() #Close the file
        
    except StopIteration: #At the end of the file, exit the loop via StopIteration.
        pass
    except Exception as e:
        print('Error: ', e)
    
  
#===============================================================================
# Main
#===============================================================================
csvopen('people.csv')
    
