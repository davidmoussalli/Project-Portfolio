
# Name: David Moussalli
# Date: 2/5/2018
# Homework: 2
# 
# I was having trouble loading the file so I had to put the absolute file location when opening it. 
# Just change the file location in the "open()" statement and it will work for you.
#
# SEE BOTTOM FOR OUTPUT.
#
##########################################################################################################
######################################### References: ####################################################
##########################################################################################################
#
# Reference 1 (Used to figure out how to read in regular expressions (to find all words in the file) and put them into a dictionary):
# 	Title: "Counting Word Frequency in a File Using Python"
# 	Author: Abder-Rahman Ali
# 	Date published: July 1, 2016
# 	Date used: January 31, 2018
# 	Availability:
# 		- https://code.tutsplus.com/tutorials/counting-word-frequency-in-a-file-using-python--cms-25965
#
# Reference 2:
# 	Title: "How do I sort a dictionary by value?"
# 	Asked by: "Gern Blanston"
# 	Answered by: "Devin Jeanpierre"
# 	Date asked: March 5, 2009
# 	Date answered: March 5, 2009
# 	Date used: February 5, 2018
# 	Availability:
# 		- https://stackoverflow.com/questions/613183/how-do-i-sort-a-dictionary-by-value



import re
import string
import operator

file  = open('C:\\Users\\User\\Desktop\\School\\6th Semester\\2000 - Python\\A2\\charactermask.txt', 'r',
             encoding = "utf8")
fileData = file.read().lower() #Read the contents of the file, but change it to
			       #  lower case since we don't care about capitalization

frequency = {} 	#Create empty dictionary to hold the frequency of all words
highest = {}	#Empty dictionary to hold the word(s) with the highest frequency
infrequent = {}		#Empty dictionary to hold the words in between unique and highest
unique = 0      #Integer that counts the number of unique words.

regExpressionSearch = re.findall(r'\b[a-z]{2,}\b', fileData) #Take everything that matches criteria from the text document...from a-z, anything greater with 2 or more letters
for word in regExpressionSearch: #count the frequency of each word.
    count = frequency.get(word,0)
    frequency[word] = count+1

frequency_list = frequency.keys() #frequency list is a list of all the words

#Sort by frequency
frequency = sorted(frequency.items(), key=operator.itemgetter(1), reverse=True) # See reference 2



highest = frequency[0: len(frequency)] #take the bottom 10 items from frequency, since they are all sorted by # of occurences
print("10 Highest: ")
for  key in range(0, 10): #Print the most frequent words
	print(key+1, ": ", highest[key])

print('\n10 Most Infrequent":')
count=0
for words, key in frequency: #Count the number of unique words
    if(key == 1):
        unique+=1
    elif(count<10): #Since frequency is sorted, after we get past the unique words, the next ones are the most infrequent.
        count+=1
        print(count, ': ',frequency[key])#Print the most infrequent words 
print("\nUnique Words: ", unique) #Print the unique words

file.close() #Close the input file.

##########################################################################################################
######################################### Output: ####################################################
##########################################################################################################


# 			Python 3.6.4 (v3.6.4:d48eceb, Dec 19 2017, 06:04:45) [MSC v.1900 32 bit (Intel)] on win32
# 			Type "copyright", "credits" or "license()" for more information.
# 			>>> 
# 			 RESTART: C:\Users\User\Desktop\School\6th Semester\2000 - Python\A2\Homework#2_DMoussalli.py 
#
# 			10 Highest: 
# 			1 :  ('the', 1506)
# 			2 :  ('of', 879)
# 			3 :  ('and', 635)
# 			4 :  ('in', 603)
# 			5 :  ('to', 547)
# 			6 :  ('is', 362)
# 			7 :  ('that', 270)
# 			8 :  ('it', 264)
# 			9 :  ('or', 263)
# 			10 :  ('they', 221)
			
# 			10 Most Infrequent":
# 			1 :  ('erich', 2)
# 			2 :  ('motives', 3)
# 			3 :  ('difference', 4)
# 			4 :  ('why', 5)
# 			5 :  ('creates', 5)
# 			6 :  ('ideological', 8)
# 			7 :  ('those', 10)
# 			8 :  ('yet', 10)
# 			9 :  ('necessarily', 10)
# 			10 :  ('soviet', 12)
			
# 			Unique Words:  2147
# 			>>> 
