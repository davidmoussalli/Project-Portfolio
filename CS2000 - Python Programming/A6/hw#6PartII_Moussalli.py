# Name: David Moussalli
# Date: 4/19/2018
# Homework: 6 Part 2
# 
#===============================================================================
# References:
#===============================================================================
# Reference 1:
#     Title: "Levenshtein Distance"
#     Publisher: python-course.eu by Bernd Klein 
#     Date used: 4/18/2018
#     Availability:
#         - https://www.python-course.eu/levenshtein_distance.php
#

#===============================================================================
# CallCounter - See Reference 1.
#   Counts the calls of MemoizeReset.
#===============================================================================
def CallCounter(func):
  def helper(*args, **kwargs):
    helper.calls += 1
    return func(*args, **kwargs)
  helper.calls = 0
  helper.__name__= func.__name__
  return helper

#===============================================================================
# MemoizeReset - See Reference 1.
#   Automatic resetting memoization decorator.
#===============================================================================
def MemoizeReset(func):
  cache = {}
  def mem(*args, **kwargs):
    key = str(args) + str(kwargs)
    if key not in cache:
      cache[key] = func(*args, **kwargs)
    return cache[key]
  return mem

#===============================================================================
# MemoizeReset - See Reference 1.
#   Automatic resetting memoization decorator.
#===============================================================================
@CallCounter
@MemoizeReset
def lev(a, b):
  if a == "":
    return len(b)
  if b == "":
    return len(a)
  if a[-1] == b[-1]:
    cost = 0
  else:
    cost = 1
  return min([lev(a[:-1], b)      + 1,
              lev(a     , b[:-1]) + 1, 
              lev(a[:-1], b[:-1]) + cost])

#===============================================================================
# Main - See Reference 1
#===============================================================================
import sys
numCalls = 0
args = sys.argv

if(len(args) == 2):
  try:
    file = open(args[1])
    for line in file:
      line = next(file)
      words = line.split(',')
      a = words[0]
      b = words[1][1:-1] #skip the leading space and the new line character.
      #Print both words, the returned value of lev(a,b), and the number of calls for that comparison.
      print(a+', '+b + ', ' + str(lev(a,b)) + ', ' + str(lev.calls-numCalls)) 
      numCalls = lev.calls
    #End for
    file.close() #Close file
  except Exception as e:
    print("Error:",e,"\nExiting program.")
else:
  print("The number of command line arguments is incorrect...\nExiting program.")
