# Name: David Moussalli
# Date: 3/2/2018
# Homework: 4
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
# Reference 1: Used to learn how to format time for correct output
# 	Title: "How to print date in a regular format in Python?"
# 	Asked by: "NomadAlien"
# 	Answered by: The StackOverflow Community - 10 users created the best response.
# 	Date asked: Nov 22 '08 at 18:37
# 	Date answered: edited Jun 15 '17 at 13:13
# 	Date used: 2/26/2018
# 	Availability:
# 		- https://stackoverflow.com/questions/311627/how-to-print-date-in-a-regular-format-in-python
#
# Reference 2: Used to learn how to print a list of emails in the correct format
# 	Title: "How to “properly” print a list?"
# 	Asked by: "Obaid"
# 	Answered by: "SingleNegationElimination"
# 	Date asked: Mar 26 '11 at 23:04
# 	Date answered: Mar 26 '11 at 23:06
# 	Date used: 2/26/2018
# 	Availability:
# 		- https://stackoverflow.com/questions/5445970/how-to-properly-print-a-list
#
# Reference 3: Used to learn how to print the dictionary of purchase history in the correct format
# 	Title: "Howto Format dict string outputs nicely"
# 	Asked by: "erikbwork"
# 	Answered by: "David Narayan"
# 	Date asked: Sep 17 '10 at 7:54
# 	Date answered: Sep 17 '10 at 12:08
# 	Date used: 2/26/2018
# 	Availability:
# 		- https://stackoverflow.com/questions/3733554/howto-format-dict-string-outputs-nicely
#
# Reference 4: Email Verification (I COPIED THIS REFERENCE'S EXAMPLE, so if necessary, deduct points. I want to make it clear this is not my work.)
# 	Title: "Python Email Verification Script"
# 	Author: "Scott Brady"
#	Publisher: www.scottbrady91.com
# 	Date published: 24 November 2015
# 	Date used: 2/27/2018
# 	Availability:
# 		- https://www.scottbrady91.com/Email-Verification/Python-Email-Verification-Script
#	Other info:
#		- The code I used is taken from the first code section titled "Email Varification Syntax"
#		- ETH: 0xC32481796340ff8A42832B12c6a687129977FE52
#		- BTC: 3PAvLKXJKL7HGceDzyZycZC4DcV7TqYpjp
#		- Paypal: paypal.me/scottbrady91
#
# Reference 5: Fixing a problem I had with the max day of the current month 
# 	Title: "Get Last Day of the Month in Python"
# 	Asked by: "Cristian"
# 	Answered by: "Blair Conrad"
# 	Date asked: asked Sep 4 '08 at 0:54
# 	Date answered: Sep 4 '08 at 12:44
# 	Date used: 2/27/2018
# 	Availability:
# 		- https://stackoverflow.com/questions/42950/get-last-day-of-the-month-in-python
#

import datetime
import calendar
import json
import re

# ========================================================================================================
# ============================ Customer ==================================================================
# ========================================================================================================
class Customer:
	def __init__(self, fName = '' , lName = '', phone = '0000000000', email = [], date = datetime.date.today(), purchaseHist = {}):
		self.fName = fName 						#First Name
		self.lName = lName						#Last Name
		self.phone = phone						#Phone Number
		self.email = email						#Email Address(es) - List
		self.purchaseHist = purchaseHist		#Purchase History - Dictionary
		self.date = date

	def __str__(self):
		#See References 1 &  2  - for the print return statement.
		return('\n%s, %s - Phone Number: %s - Email Address(es): %s - Date: %s - Purchase history:' %(self.lName, self.fName, self.phone, ', '.join(map(str, self.email)), self.date.strftime('%B %d, %Y')
			)
		 ) + json.dumps(self.purchaseHist, indent = 4) #See Reference 3

	def add_contact(self): #What is this supposed to do??????
		# Ask the customer which information they want to change.
		return self

	def look_contact(self, lastName=''):
		# If multiple contacts have same last name, ask for first...then phone number
		pass

# ========================================================================================================
# ============================ ItemToPurchase ============================================================
# ========================================================================================================
class ItemToPurchase:
	def __init__(self, item_name = '', item_price = 0.0, item_quantity = 0, item_description = ''):
		self.item_name = item_name
		self.item_price = item_price
		self.item_quantity = item_quantity
		self.item_description = item_description

	def __str__(self):
		return('%s: %s.' % (self.item_name, self.item_description))

	def print_item_cost(self):
		try:
			if self.item_price <= 0.0:
				raise ValueError('Error: Item price is <= 0...')	
			else:
				print('%s %d @ $%s = $%s' % (self.item_name, self.item_quantity, f'{self.item_price:.{2}f}', f'{(self.item_quantity * self.item_price):.{2}f}') )
		except ValueError as excpt:
			print(excpt)

# ========================================================================================================
# ============================ ShoppingCart ==============================================================
# ========================================================================================================
class ShoppingCart:
	def __init__(self, customer_name = '', current_date = datetime.date.today()):
		self.customer_name = customer_name
		self.current_date = current_date
		self.cart_items = []

	def add_item(self, ItemToPurchase):
		self.cart_items.append(ItemToPurchase)

	def remove_item(self, itemName):
		flag = False
		try:
			for item in self.cart_items:
				if item.item_name == itemName:
					flag = True
					self.cart_items.remove(item)
			if flag == False:
				raise Exception('Item not found in cart. Nothing modified.')
		except Exception as e:
			print(e)
		#print() # Extra print for code correctness

	def modify_item(self, ItemToPurchase): 
		print('Enter the new quantity:', end=' ')
		qty = 0
		while True: #This loop verifies quantity input
			try:
				qty = int(input())
				if qty < 0:
					raise Exception('Quantity must be a positive integer!...Re-enter the quantity for: %s' % ItemToPurchase.item_name)
				elif qty == 0: # If quantity '0' is entered, just delete the item from the shopping cart.
					print('Quantity \'0\' was entered. The item was deleted.')
					self.remove_item(ItemToPurchase.item_name)
				else:
					ItemToPurchase.item_quantity = qty
					break
			except Exception as e:
				print('Quantity must be a positive integer!...Re-enter the quantity for: %s' % ItemToPurchase.item_name)
		#End while

	def return_item(self, Customers):
		print('RETURN ITEM')
		print('Enter the customer name:')
		try:
			flag1 = False
			cName = input() #Get customer name from user
			for customer in Customers:
				if cName == (customer.fName+" "+customer.lName):
					flag1 = True
					print(customer, '\n') #Print customer

					print('Enter the name of item to return:')
					flag2 = False
					iName = input() #Get item name from user
					if iName in customer.purchaseHist:
						flag2 = True
						del customer.purchaseHist[iName] #Delete the item from purchase history
						print('The item found and returned successfully.')
						break #Break out of item search loop if item found.
					if flag2 == False:
						print('The item is not found.')
					break #Break out of customer search loop if customer found.
			if flag1 == False:
				print('Customer not found.')
		except Exception as e:
			print(e)

	def get_num_items_in_cart(self):
		return len(self.cart_items)

	def get_cost_of_cart(self):
		cartCost = 0.0
		for item in self.cart_items:
			cartCost += (item.item_quantity * float(item.item_price))
		return cartCost

	def print_total(self):
		print('\nOUTPUT SHOPPING CART')
		try:
			if self.get_num_items_in_cart() > 0:
				print('%s\'s Shopping Cart - %s' % (self.customer_name, self.current_date))
				print('Number of Items: %d\n' % self.get_num_items_in_cart())
				for item in self.cart_items:
					print('%s %d @ $%s = $%s' % (item.item_name, item.item_quantity, f'{item.item_price:.{2}f}', f'{(item.item_quantity * item.item_price):.{2}f}'))
				print('\nTotal: $%s' % (f'{self.get_cost_of_cart():.{2}f}'))
			else:
				raise ValueError('SHOPPING CART IS EMPTY.')
		except ValueError as excpt:
			print(excpt)

	def print_descriptions(self):
		if self.get_num_items_in_cart() > 0:
			for item in self.cart_items:
				print('%s: %s' % (item.item_name, item.item_description))
		else:
			print('No items in cart.')


# ============================ verifyEmail ===============================================================
# ****************** Disclaimer: This method is NOT my code ********* See Reference 4 ********************
def verifyEmail(email):
	#The code in this method is taken from Reference 4 (listed at the top of the page).
	#I could not figure out how to verify an email without using too much of this code,
	#	so I decided to use this and clearly give full credit to its creator.

 	#Check if the email string matches the regex for email addresses...
	emailMatch = re.match('^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$', email)
	if emailMatch == None: # If it doesn't, raise an error and return False...
		raise ValueError('Bad email address syntax, please enter again...')
		return False
	return True # Otherwise, return true.
#****************** End of Reference 4's code ************************************************************

# ============================ printMenu =================================================================
def printMenu():
	print('\nMENU')
	print('a - Add item to cart')
	print('r - Remove item from cart')
	print('c - Change item quantity')
	print('u - Return items')
	print('i - Output items\' descriptions')
	print('o - Output shopping cart')
	print('q - Quit\n')

def fillDictionary(ShoppingCart1, customerPurchHist):
	#Fill {} with shopping cart items, then return it
	pHist = customerPurchHist # Empty dictionary represents purchase history
	for item in ShoppingCart1.cart_items:
		itemName = item.item_name
		itemQuantity = item.item_quantity

		if itemName not in pHist:
			pHist[itemName] = itemQuantity
		else:
			pHist[itemName] += itemQuantity
		#
	return pHist



# ============================ Main ======================================================================
options = ['a', 'r', 'c', 'u', 'i', 'o', 'q'] #List of options for user
numUsers = 0
while True: # Get number of customers
	print('Enter the number of customers:')
	try:
		numUsers = int(input()) 
		if(numUsers < 1):
			raise Exception('You must have at least 1 customer!...Re-enter the number of customers:')
		else: 
			break
	except Exception as e:
		print(e)
#End while

fName = [None for a in range(numUsers+1)]			#First names of users - List
lName = [None for b in range(numUsers+1)]			#Last names of users - List
phone = [None for c in range(numUsers+1)]			#Phone numbers of users - List
email = [[0] for d in range(numUsers+1)]			#Email address(es) of users - List
date = datetime.date.today()						#Current date

purchHist = [None for e in range(numUsers+1)]		#Purchase histories - List
Customers = [None for f in range(numUsers+1)]		#Customers - List
customer_name = [None for g in range(numUsers+1)]	#Customer Names - List
ShoppingCarts = [None for h in range(numUsers+1)] 	#Shopping Carts - List


for i in range(numUsers): #Start of fill customers loop
	print('Enter customer info. #%d' % (i+1))
	print('Enter customer\'s first name:')
	while True:
		try:
			tempFName = input() 
			if(tempFName == ''):
				raise Exception('No input entered...Re-enter customer\'s first name:')
			else:
				fName[i] = tempFName
				break
		except Exception as e:
			print(e)
	#End while

	print('Enter customer\'s last name:')
	while True:
		try:		
			tempLName = input()
			if(tempLName == ''):
				raise Exception('No input entered...Re-enter customer\'s last name:')
			else:
				lName[i] = tempLName
				break
		except Exception as e:
			print(e)
	#End while

	print('Enter customer\'s phone no.:')
	while True:
		try:		
			tempPhone = input() 
			if(tempPhone == '' or (len(tempPhone) < 10) or int(tempPhone)<0):
				raise Exception('Input must be 10 digits...Re-enter customer\'s phone no.:')
			else:
				phone[i] = tempPhone
				break
		except Exception as e:
			print(e)
	#End while

	print('Enter customer\'s email address(es):')
	while True:
		try:		
			tempEmail = input() 
			if(verifyEmail(tempEmail) == False): # See Reference 4 for verifyEmail().
				raise Exception('Email address format incorrect...Re-enter customer\'s email address.:')
			else:
				email[i][0] = tempEmail 
				while True:
					try:
						print('Enter another email? (enter email, otherwise enter \'n\'):')
						tempEmail = input()
						if(tempEmail == 'n'):
							break
						elif(verifyEmail(tempEmail) == False): # See Reference 4 for verifyEmail().
							raise Exception('Email address format incorrect...Re-enter customer\'s email address.:')
						else:
							email[i].append(tempEmail)
					except Exception as e2:
						print(e2)
				#End while
				break
		except Exception as e:
			print(e)
	#End while

	print('Enter today date:')
	print('\tEnter the year (between 1900 - 2018):', end=' ')
	while True:
		try:		
			year = int(input())			
			if(year > 2018):
				raise Exception('\tYear must be between 1900 - 2018! Re-enter year:')
			elif(year < 1900):
				raise Exception('\tYear must be between 1900 - 2018! Re-enter year:')
			else:
				break
		except Exception as e:
			print(e)
	#End while
	
	print('\tEnter the month (between 1 - 12):', end=' ')
	while True:
		try:		
			month = int(input()) 			
			if(month > 12):
				raise Exception('\tMonth must be between 1 - 12! Re-enter month:')
			elif(month <1):
				raise Exception('\tMonth must be between 1 - 12! Re-enter month:')
			else:
				break
		except Exception as e:
			print(e)
	#End while
	
	# Get current max day of the current year month:
	maxDayOfCurMonthcalendar = calendar.monthrange(year, month)[1] # See Reference 5
	print('\tEnter the day (between 1 and %d): ' % maxDayOfCurMonthcalendar) # See Reference 5
	while True:
		try:		
			day = int(input()) 
			if(day > maxDayOfCurMonthcalendar):
				raise Exception()
			elif(day < 1):
				raise Exception()
			else:
				break
		except Exception as e:
			print('\tDay must be between 1 - %d! Re-enter day:' % maxDayOfCurMonthcalendar) # See Reference 5
			print(e)
	#End while

	date = datetime.date(year, month, day)
	purchHist[i] = {}
	Customers[i] = Customer(fName[i], lName[i], phone[i], email[i], date, purchHist[i])
	customer_name[i] = fName[i]+' '+lName[i]
	ShoppingCarts[i] = ShoppingCart(customer_name[i], date.strftime('%B %d, %Y'))

	print(Customers[i], '\n')

# End of fill customers for-loop


menuChoice = ''
curUser = 0 #Keeps track of which user you are currently modifying.
print('Customer name: %s' % (customer_name[curUser]))
print('Today\'s date: %s' % (Customers[curUser].date.strftime('%B %d, %Y')))

while curUser<=numUsers:
	printMenu()
	print('Choose an option:')
	menuChoice = input()
	if menuChoice not in options:
		print('You must select an option from the list!')

#============================ - Add item to cart
	elif menuChoice == 'a':
		itemName = ''
		itemDescription = ''
		itemPrice = 0.00
		itemQuantity = 0

		print('\nADD ITEM TO CART')
		print('Enter the item name:')
		while True:
			input1 = input()
			if input1 == '':
				print('No item name was entered...Re-enter the item name:')
			else:
				itemName = input1
				break
		#End while

		print('Enter the item description:')
		while True:
			input1 = input()
			if input1 == '':
				print('No item description was entered...Re-enter the item description:')
			else:
				itemDescription = input1
				break
		#End while

		print('Enter the item price:')
		while True:
			try:
				input1 = float(input())
				if input1 == '':
					raise Exception('No item price was entered...Re-enter the item price:')
				elif input1 < 0:
					raise Exception('Price must be > 0...Re-enter the item price:')
				else:
					itemPrice = input1
					break
			except Exception as e:
				print(e)
		#End while

		print('Enter the item quantity:')
		while True:
			try:
				input1 = int(input())
				if input1 == '':
					raise Exception('No item quantity was entered...Re-enter the item quantity:')
				elif input1 < 0:
					raise Exception('quantity must be > 0...Re-enter the item quantity:')
				else:
					itemQuantity = input1
					break
			except Exception as e:
				print(e)
		#End while

		itemToBePurchased = ItemToPurchase(itemName, itemPrice, itemQuantity, itemDescription)
		ShoppingCarts[curUser].add_item(itemToBePurchased);

#============================ - Remove item from cart
	elif menuChoice == 'r':
		print('REMOVE ITEM FROM CART')
		print('Enter name of item to remove:')
		while True:
			try:
				input1 = input()
				if input1 == None:
					raise Exception('No item name entered...Re-enter item name:')
				else:
					break
			except Exception as e:
				print(e)
		ShoppingCarts[curUser].remove_item(input1)

#============================ - Change item quantity
	elif menuChoice == 'c':
		flag = False
		print('CHANGE ITEM QUANTITY')
		print('Enter the item name:')
		itemName = input()
		try:
			for item in ShoppingCarts[curUser].cart_items:
				if item.item_name == itemName:
					flag = True
					ShoppingCarts[curUser].modify_item(item)
			if(flag == False):
				raise Exception('Item not found in cart. Nothing modified.')
		except Exception as e:
			print(e)

#============================ - Return items
	elif menuChoice == 'u': 
		#Call return_item() and send the list of Customers as a parameter.
		ShoppingCarts[curUser].return_item(Customers)

#============================ - Output items' descriptions
	elif menuChoice == 'i':
		print('\nOUTPUT ITEMS\' DESCRIPTIONS')
		print('%s\'s Shopping cart - %s' % (ShoppingCarts[curUser].customer_name, ShoppingCarts[curUser].current_date))
		print('Item Descriptions')
		ShoppingCarts[curUser].print_descriptions()

#============================ - Output shopping cart (Check out)
	elif menuChoice == 'o':
		ShoppingCarts[curUser].print_total() 
		Customers[curUser].purchaseHist = fillDictionary(ShoppingCarts[curUser], Customers[curUser].purchaseHist) #Add the cart items to the dictionary
		ShoppingCarts[curUser].cart_items = [] #Empty the cart

#============================ - Quit
	elif menuChoice == 'q': 
		curUser+=1
		if(curUser<numUsers):
			print('Customer name: %s' % (customer_name[curUser]))
			print('Today\'s date: %s' % (Customers[curUser].date.strftime('%m/%d/%Y')))
