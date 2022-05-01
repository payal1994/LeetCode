/*
Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.

You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.

 

Example 1:

Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
Example 2:

Input: nums = [-1,0]
Output: [-1,0]
Example 3:

Input: nums = [0,1]
Output: [1,0]
 

Constraints:

2 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each integer in nums will appear twice, only two integers will appear once.


Solution:
Overview
The problem could be solved in \mathcal{O}(N)O(N) time and \mathcal{O}(N)O(N) space by using hashmap.

To solve the problem in a constant space is a bit tricky but could be done with the help of two bitmasks.

fig


Approach 1: Hashmap
Build a hashmap : element -> its frequency. Return only the elements with the frequency equal to 1.

Implementation


Complexity Analysis

Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

Space complexity : \mathcal{O}(N)O(N) to keep the hashmap of NN elements.


Approach 2: Two bitmasks
Prerequisites

This article will use two bitwise tricks, discussed in details last week :

If one builds an array bitmask with the help of XOR operator, following bitmask ^= x strategy, the bitmask would keep only the bits which appear odd number of times. That was discussed in details in the article Single Number II.
fig

x & (-x) is a way to isolate the rightmost 1-bit, i.e. to keep the rightmost 1-bit and to set all the others bits to zero. Please refer to the article Power of Two for the detailed explanation.
fig

Intuition

An interview tip. Imagine, you have a problem to indentify an array element (or elements), which appears exactly given number of times. Probably, the key is to build first an array bitmask using XOR operator. Examples: In-Place Swap, Single Number, Single Number II.

So let's create an array bitmask : bitmask ^= x. This bitmask will not keep any number which appears twice because XOR of two equal bits results in a zero bit a^a = 0.

Instead, the bitmask would keep only the difference between two numbers (let's call them x and y) which appear just once. The difference here it's the bits which are different for x and y.

fig

Could we extract x and y directly from this bitmask? No. Though we could use this bitmask as a marker to separate x and y.

Let's do bitmask & (-bitmask) to isolate the rightmost 1-bit, which is different between x and y. Let's say this is 1-bit for x, and 0-bit for y.

fig

Now let's use XOR as before, but for the new bitmask x_bitmask, which will contain only the numbers which have 1-bit in the position of bitmask & (-bitmask). This way, this new bitmask will contain only number x x_bitmask = x, because of two reasons:

y has 0-bit in the position bitmask & (-bitmask) and hence will not enter this new bitmask.

All numbers but x will not be visible in this new bitmask because they appear two times.

fig

Voila, x is identified. Now to identify y is simple: y = bitmask^x.

Implementation


Complexity Analysis

Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

Space complexity : \mathcal{O}(1)O(1), it's a constant space solution.

*/

class Solution {
    public int[] singleNumber(int[] digits) {
     
           Map<Integer,Integer> map =new HashMap<Integer,Integer>();
            for(int i=0;i<digits.length;i++){
               if(map.containsKey(digits[i])){
                   map.put(digits[i],(map.get(digits[i]))+1);
               }
               else{
                   map.put(digits[i],1);
               }
            }
        int b[]=new int[2];
            int k=0;
            for(Map.Entry<Integer,Integer> key : map.entrySet()){
                if(key.getValue()==1) {
                    b[k]=key.getKey();
                    k++;
                }
                    
            }
            return b;
    }
}
