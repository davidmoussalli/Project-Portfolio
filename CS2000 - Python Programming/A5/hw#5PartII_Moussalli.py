#===============================================================================
# Name: David Moussalli
# Date: 4/3/2018
# Homework: 5 Part 2
# 
# REFERENCES:
#     Reference 1: Insertion Sort
#         Title: "Insertion Sort"
#         Publisher: GeeksforGeeks
#         Author: Mohit Kumra
#         Date used: 4/2/2018
#         Availability:
#               - https://www.geeksforgeeks.org/insertion-sort/ 
#
#     Reference 2: Quick Sort
#         Title: "QuickSort"
#         Publisher: GeeksforGeeks
#         Author: Mohit Kumra
#         Date used: 4/2/2018
#         Availability:
#               - https://www.geeksforgeeks.org/quick-sort/
#
#===============================================================================

import random
import time
from sys import setrecursionlimit

#===============================================================================
# Generic swap function
#
# Swaps 2 given values in an array.
#===============================================================================
def swap(array, a, b):
    temp = array[a]
    array[a] = array[b]
    array[b] = temp

#===============================================================================
# Partition method for quickSort - See Reference 2
#
# Uses the last element as a pivot, places the pivot element at its correct
#    position in the sorted array, and places all smaller elements to the left
#    of the pivot, and all larger elements to the right of the pivot.
#===============================================================================
def partition(array, low, high):
    i = low-1
    pivot = array[high]
    
    for j in range(low, high): #Traverse
        if array[j] <=  pivot:
            i += 1
            swap(array, i, j) #Swap
    swap(array, i+1, high)
    return i+1

#===============================================================================
# Generic quick sort method - See Reference 2
#
# Calls partition, then recursively calls quickSort to the left and right 
#    and right of the partition index.
#===============================================================================
def quickSort(array, low, high):
    if low < high:
        partitionIndex = partition(array, low, high) #Set partitionIndex
        
        quickSort(array, low, partitionIndex-1) #Recursively sort the left side of partitionIndex
        quickSort(array, partitionIndex+1, high) #Recursively sort the right side of partitionIndex

#===============================================================================
# Generic insertion sort method - See Reference 1
#
# Finds the smallest element and stores it at the first index, then the second 
#    smallest and stores it at the second index, and so on, until the entire
#    array is sorted.
#===============================================================================
def insertionSort(array):
    for i in range(0, len(array)): 
        key = array[i]
        
        j = i-1 #Move each element greater than the key one position foreward.
        while j >= 0 and key < array[j]: 
            array[j+1] = array[j]
            j -= 1
        array[j+1] = key

#===============================================================================
# Generic bubble sort method
#
# Goes through every array element and compares it to every other element
#     before it. If an element is greater than the element before it,
#     it will swap them.
#===============================================================================
def bubbleSort(array):
    for i in range(len(array)): #Traverse
        for j in range (0, len(array)-i-1): #Compare
            if array[j] > array[j+1]:
                swap(array, j, j+1) #Swap
    
#===============================================================================
#             Main method
#===============================================================================
def main():
    startTime=0
    stopTime=0
    n = 1000
    setrecursionlimit(n) #Set recursion limit
    print("Sorting", n, "items...\n")
    
    if n < 50000:
        array = []
        for i in range(n): #Fill array with n random numbers
            array.append(random.randint(1, n))
        
        startTime = time.time()
        bubbleSort(array)       #Bubble sort the array of n items
        stopTime = time.time()
        print("Total bubble sort time: ", stopTime - startTime, "seconds")
    else:
        print("Skipping over bubble sort for > 50,000 items...")
#------------   
    if n < 50000:     
        array = []
        for i in range(n): #Fill array with n random numbers
            array.append(random.randint(1, n))
            
        startTime = time.time()
        insertionSort(array)       #Insertion sort the array of n items
        stopTime = time.time()
        print("Total insertion sort time: ", stopTime - startTime, "seconds")
    else:
        print("Skipping over bubble sort for > 50,000 items...")
#------------    
    array = []
    for i in range(n): #Fill array with n random numbers
        array.append(random.randint(1, n))
        
    startTime = time.time()
    quickSort(array, 0, len(array)-1)       #Quick sort the array of n items
    stopTime = time.time()
    print("Total quick sort time: ", stopTime - startTime, "seconds")


if __name__ == "__main__":
    main()
