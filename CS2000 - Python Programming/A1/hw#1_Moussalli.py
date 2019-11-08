# Name: David Moussalli
#Date: 1/20/2018
# Homework: 1

########################################################################################
#References:
# 
# For figuring out how to convert int to binary (part 4)
#	Title:  "Python int to binary?"
# 	Asked by: "Nate" on March 31, 2009
# 	Answered by: "John Fouhy" on March 31, 2009
# 	Date used: Wednesday, January 24, 2018
# 	Availability:
# 		https://stackoverflow.com/questions/699866/python-int-to-binary
# 
# 
# For figuring out part 5:
# 	Title: "One line if statement in Python (ternary conditional operator)"
# 	Author: Anton Caceres
# 	Published: Thursday, January 31, 2013
# 	Date used: Wednesday, January 24, 2018
# 	Availability:
# 		https://www.pythoncentral.io/one-line-if-statement-in-python-ternary-conditional-operator/
#
########################################################################################




# PART 1
print('Please enter a string: ', end=' ')
input1 = str(input())
print('Please enter a second string: ', end=' ')
input2 = str(input())

if input1 in input2: #If input1 string is contained anywhere in input2 string, print true...Print false otherwise.
	print("True")
else:
	print("False")
	
print()#extra line




# PART 2
print('Please enter a string:', end=' ')
palindromeString = str(input())
if str(palindromeString) == str(palindromeString)[::-1]: #If the string is a palindrome print true, print false otherwise.
        print('True')
else:
        print('False')
        
print()#extra line



# PART 3
print('Please enter a number: ', end =' ')
number1 = int(input())
print('Please enter a another number: ', end =' ')
number2 = int(input())
print(int(number1) + int(number2)) #Print the two sum of the two integers.

print()#extra line



# PART 4 - (See Reference 1)
print('Please enter a number: ', end =' ')
binaryNumber = int(input())
print(str(bin(binaryNumber))[2:]) #Print the binary version of the number, but skip the first 2 characters.

print()#extra line




# PART 5 - (See Reference 2)
print('Please enter a string: ', end=' ')
input3 = str(input())
print('Please enter a second string: ', end=' ')
input4 = str(input())

print({True: 't', False: 'f'}[input3 in input4])
# The above statement will print 't' if "input3 in input4" returns True, and 'f' if it returns False.
# It is one Alternative to the Ternary Operator

