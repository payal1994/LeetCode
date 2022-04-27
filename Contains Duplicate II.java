/*
Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

 

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
0 <= k <= 105

Solution:
Approach #1: Naive Linear Search
Intuition

Look for duplicate element in the previous kk elements.

Algorithm

This algorithm is the same as Approach #1 in Contains Duplicate solution, except that it looks at previous kk elements instead of all its previous elements.

Another perspective of this algorithm is to keep a virtual sliding window of the previous kk elements. We scan for the duplicate in this window.


Complexity Analysis

Time complexity : O(n \min(k,n))O(nmin(k,n)). It costs O(\min(k, n))O(min(k,n)) time for each linear search. Apparently we do at most nn comparisons in one search even if kk can be larger than nn.

Space complexity : O(1)O(1).

Approach #2: Binary Search Tree
Intuition

Keep a sliding window of kk elements using self-balancing Binary Search Tree (BST).

Algorithm

The key to improve upon Approach #1 above is to reduce the search time of the previous kk elements. Can we use an auxiliary data structure to maintain a sliding window of kk elements with more efficient search, delete, and insert operations? Since elements in the sliding window are strictly First-In-First-Out (FIFO), queue is a natural data structure. A queue using a linked list implementation supports constant time delete and insert operations, however the search costs linear time, which is no better than Approach #1.

A better option is to use a self-balancing BST. A BST supports search, delete and insert operations all in O(\log k)O(logk) time, where kk is the number of elements in the BST. In most interviews you are not required to implement a self-balancing BST, so you may think of it as a black box. Most programming languages provide implementations of this useful data structure in its standard library. In Java, you may use a TreeSet or a TreeMap. In C++ STL, you may use a std::set or a std::map.

If you already have such a data structure available, the pseudocode is:

Loop through the array, for each element do
Search current element in the BST, return true if found
Put current element in the BST
If the size of the BST is larger than kk, remove the oldest item.
Return false

Complexity Analysis

Time complexity : O(n \log (\min(k,n)))O(nlog(min(k,n))). We do nn operations of search, delete and insert. Each operation costs logarithmic time complexity in the sliding window which size is \min(k, n)min(k,n). Note that even if kk can be greater than nn, the window size can never exceed nn.

Space complexity : O(\min(n,k))O(min(n,k)). Space is the size of the sliding window which should not exceed nn or kk.

Note

The algorithm still gets Time Limit Exceeded for large nn and kk.

Approach #3: Hash Table
Intuition

Keep a sliding window of kk elements using Hash Table.

Algorithm

From the previous approaches, we know that even logarithmic performance in search is not enough. In this case, we need a data structure supporting constant time search, delete and insert operations. Hash Table is the answer. The algorithm and implementation are almost identical to Approach #2.

Loop through the array, for each element do
Search current element in the HashTable, return true if found
Put current element in the HashTable
If the size of the HashTable is larger than kk, remove the oldest item.
Return false

Complexity Analysis

Time complexity : O(n)O(n). We do nn operations of search, delete and insert, each with constant time complexity.

Space complexity : O(\min(n,k))O(min(n,k)). The extra space required depends on the number of items stored in the hash table, which is the size of the sliding window, \min(n,k)min(n,k).

*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
       //approtch 1
   //     for(int i=0;i<nums.length-1;i++) {
//            for(int j=i+1;j<nums.length;j++){
//                if(nums[i]==nums[j]){
//                    if (!((j-i)<=k))
//                        return false;
//                }
//            }
//        }
        
        
        //approch 2
        
        Set<Integer> set=new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if(set.size()>k)
                set.remove(nums[i-k]);
            
        }
        return false;
        
            
        
    }
}
