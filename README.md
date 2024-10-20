### Application Architecture Description ###  
This multithreaded application is designed to process a large number of randomly generated strings, classifying them as "beautiful"
based on certain conditions (palindrome, single character, or sorted order). The application utilizes multiple threads to improve
performance by concurrently analyzing the strings based on different conditions. Below is a breakdown of the key architectural 
components:

## 1. Main Components: ##  
Data Structures:
- AtomicInteger countThree, countFour, countFive: These atomic integers track the number of "beautiful" words of lengths 3, 4, and 5,  
respectively. Atomic variables are used to ensure thread-safe operations across multiple threads.  
Input:
- texts: An array of 100,000 random strings of length between 3 and 5 characters. These strings are generated in the main thread using  
the generateText function.  

## 2. Multithreading: ##  
Threads: Three separate threads are created to analyze the strings based on different criteria:  
-  Thread 1 (Palindrome Analysis): This thread checks if the string is a palindrome (i.e., it reads the same forwards and backwards) and  
increments the appropriate counter based on the length of the string.  
-  Thread 2 (Single Character Analysis): This thread checks if the string consists of the same repeated character (e.g., "aaa"). If so, it  
increments the respective counter.  
-  Thread 3 (Sorted String Analysis): This thread checks if the string's characters are in sorted order (e.g., "abc"). If so, it increments  
the respective counter.  
-  Runnable Logic: Each thread is given a specific task to analyze all strings using a particular logic (palindrome, single character, sorted). 
Each thread goes through the entire texts array and checks whether the string meets the defined condition. It updates the appropriate atomic counters 
(countThree, countFour, countFive) based on the string's length.   

## 3. Thread Synchronization: ##  
The program uses the join() method to ensure that the main thread waits for all the worker threads to finish their execution before proceeding 
to print the final results. This ensures that all analysis is completed before displaying the counts of "beautiful" words.

## 4. Methods: ##  
- isPalindrome(String text): Checks if a given string is a palindrome by comparing characters from both ends moving toward the center.  
- isSingleChar(String text): Checks if all characters in the string are identical.  
- isSorted(String text): Checks if the characters in the string are in non-decreasing order.  
- generateText(String letters, int length): Generates a random string of the specified length using the characters from letters (in this case, "abc").  

## 5. Atomic Variables: ##  
The use of AtomicInteger ensures that the counter updates are thread-safe, preventing race conditions that could occur if multiple threads tried 
to increment the same variable simultaneously.

##  6. Execution Flow: ##  
- The main thread generates an array of 100,000 random strings with lengths of 3, 4, or 5.   
Three threads are created to analyze the strings based on the following conditions:  
- Palindrome check  
- Single character check  
- Sorted order check  
Each thread updates atomic counters for the number of "beautiful" words based on the conditions and string lengths.  
After all threads complete, the program outputs the counts of "beautiful" words for each string length (3, 4, and 5).  

##  7. Output: ##  
The program prints the final counts of "beautiful" words of lengths 3, 4, and 5 based on the three conditions 
(palindrome, single character, or sorted).

## Summary: ##
This application uses a multithreaded approach to efficiently process a large dataset of random strings. The architecture relies on thread-safe
atomic counters, separate threads for different conditions, and synchronization through the join() method to ensure accurate results. This 
structure improves the performance by parallelizing the analysis tasks while ensuring thread safety through atomic operations.