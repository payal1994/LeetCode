/*
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

 

Example 1:

Input: nums = [1,2,3,1]
Output: true
Example 2:

Input: nums = [1,2,3,4]
Output: false
Example 3:

Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109

SOLUTION:
his article is for beginners. It introduces the following ideas: Loop Invariant, Linear Search, Sorting and Hash Table.

Solution
Approach #1 (Naive Linear Search) [Time Limit Exceeded]
Intuition

For an array of nn integers, there are C(n,2) = \frac{n(n-1)}{2}C(n,2)= 
2
n(n−1)
​
  pairs of integers. Thus, we may check all \frac{n(n-1)}{2} 
2
n(n−1)
​
  pairs and see if there is any pair with duplicates.

Algorithm

To apply this idea, we employ the linear search algorithm which is the simplest search algorithm. Linear search is a method of finding if a particular value is in a list by checking each of its elements, one at a time and in sequence until the desired one is found.

For our problem, we loop through all nn integers. For the iith integer nums[i], we search in the previous i-1 integers for the duplicate of nums[i]. If we find one, we return true; if not, we continue. Return false at the end of the program.

To prove the correctness of the algorithm, we define the loop invariant. A loop invariant is a property that holds before (and after) each iteration. Knowing its invariant(s) is essential for understanding the effect of a loop. Here is the loop invariant:

Before the next search, there are no duplicate integers in the searched integers.

The loop invariant holds true before the loop because there is no searched integer. Each time through the loop we look for any any possible duplicate of the current element. If we found a duplicate, the function exits by returning true; If not, the invariant still holds true.

Therefore, if the loop finishes, the invariant tells us that there is no duplicate in all nn integers.


Complexity Analysis

Time complexity: O(n^2)O(n 
2
 ). In the worst case, there are \frac{n(n-1)}{2} 
2
n(n−1)
​
  pairs of integers to check. Therefore, the time complexity is O(n^2)O(n 
2
 ).

Space complexity: O(1)O(1). We only used constant extra space.

Note

This approach will get Time Limit Exceeded on LeetCode. Usually, if an algorithm is O(n^2)O(n 
2
 ), it can handle nn up to around 10^410 
4
 . It gets Time Limit Exceeded when n \geq 10^5n≥10 
5
 .

Approach #2 (Sorting) [Accepted]
Intuition

If there are any duplicate integers, they will be consecutive after sorting.

Algorithm

This approach employs sorting algorithm. Since comparison sorting algorithm like heapsort is known to provide O(n \log n)O(nlogn) worst-case performance, sorting is often a good preprocessing step. After sorting, we can sweep the sorted array to find if there are any two consecutive duplicate elements.


Complexity Analysis

Time complexity: O(n \log n)O(nlogn). Sorting is O(n \log n)O(nlogn) and the sweeping is O(n)O(n). The entire algorithm is dominated by the sorting step, which is O(n \log n)O(nlogn).

Space complexity: O(1)O(1). Space depends on the sorting implementation which, usually, costs O(1)O(1) auxiliary space if heapsort is used.

Note

The implementation here modifies the original array by sorting it. In general, it is not a good practice to modify the input unless it is clear to the caller that the input will be modified. One may make a copy of nums and operate on the copy instead.

Approach #3 (Hash Table) [Accepted]
Intuition

Utilize a dynamic data structure that supports fast search and insert operations.

Algorithm

From Approach #1 we know that search operations is O(n)O(n) in an unsorted array and we did so repeatedly. Utilizing a data structure with faster search time will speed up the entire algorithm.

There are many data structures commonly used as dynamic sets such as Binary Search Tree and Hash Table. The operations we need to support here are search() and insert(). For a self-balancing Binary Search Tree (TreeSet or TreeMap in Java), search() and insert() are both O(\log n)O(logn) time. For a Hash Table (HashSet or HashMap in Java), search() and insert() are both O(1)O(1) on average. Therefore, by using hash table, we can achieve linear time complexity for finding the duplicate in an unsorted array.


Complexity Analysis

Time complexity: O(n)O(n). We do search() and insert() for nn times and each operation takes constant time.

Space complexity: O(n)O(n). The space used by a hash table is linear with the number of elements in it.

Note

For certain test cases with not very large nn, the runtime of this method can be slower than Approach #2. The reason is hash table has some overhead in maintaining its property. One should keep in mind that real world performance can be different from what the Big-O notation says. The Big-O notation only tells us that for sufficiently large input, one will be faster than the other. Therefore, when nn is not sufficiently large, an O(n)O(n) algorithm can be slower than an O(n \log n)O(nlogn) algorithm.
*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
      Map<Integer,Integer> map= new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                return true;
            }
            else
                map.put(key,1);
        }
        return false;
    }
}
