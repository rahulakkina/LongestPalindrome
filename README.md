# Longest Palindrome Strings

Longest palindromic substring. In computer science, the longest palindromic substring or longest symmetric factor problem is the problem of finding a maximum-length contiguous substring of a given string that is also a palindrome. For example, the longest palindromic substring of "bananas" is "anana".

One can compute them using following approaches 

Brute Force(Naive Approach) - Involves time complexity: O(n^3) and Space complexitity of OMG(1)
Dynamic Programming - Involves time complexity: O(n^2) and Space complexitity of OMG(n^2)
Manacher's Algorithm - Involves time complexity: O(n) and Space complexitity of OMG(n^2)


The underliying example is a Scala implementation on Manacher's Algorithm to compute Longest unique Palindrome Strings. For example 

Input String is : sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop

Top 5 Unique logest palindrome strings are below.

Text: hijkllkjih, Index: 23, Length: 10
Text: defggfed, Index: 13, Length: 8
Text: abccba, Index: 5, Length: 6
Text: qrrq, Index: 1, Length: 4
Text: mnnm, Index: 35, Length: 4


