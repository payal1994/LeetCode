/*
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

Example 1:

Input: nums = [2,2,3,2]
Output: 3
Example 2:

Input: nums = [0,1,0,1,0,1,99]
Output: 99
 

Constraints:

1 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each element in nums appears exactly three times except for one element which appears once.

Solution:
Overview
The problem seems to be quite simple and one could solve it in \mathcal{O}(N)O(N) time and \mathcal{O}(N)O(N) space by using an additional data structure like set or hashmap.

The real game starts at the moment when Google interviewer (the problem is quite popular at Google the last six months) asks you to solve the problem in a constant space, testing if you are OK with bitwise operators.

fig



Approach 1: HashSet
The idea is to convert an input array into hashset and then to compare the tripled sum of the set with the array sum

3 \times (a + b + c) - (a + a + a + b + b + b + c) = 2 c3×(a+b+c)−(a+a+a+b+b+b+c)=2c

Implementation


Complexity Analysis

Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

Space complexity : \mathcal{O}(N)O(N) to keep the set of N/3N/3 elements.


Approach 2: HashMap
Let's iterate over the input array to count the frequency of each number, and then return an element with a frequency 1.

Implementation


Complexity Analysis

Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

Space complexity : \mathcal{O}(N)O(N) to keep the hashmap of N/3N/3 elements.


Approach 3: Bitwise Operators : NOT, AND and XOR
Intuition

Now let's discuss \mathcal{O}(1)O(1) space solution by using three bitwise operators

\sim x \qquad \textrm{that means} \qquad \textrm{bitwise NOT}∼xthat meansbitwise NOT

x \& y \qquad \textrm{that means} \qquad \textrm{bitwise AND}x&ythat meansbitwise AND

x \oplus y \qquad \textrm{that means} \qquad \textrm{bitwise XOR}x⊕ythat meansbitwise XOR

XOR

Let's start from XOR operator which could be used to detect the bit which appears odd number of times: 1, 3, 5, etc.

XOR of zero and a bit results in that bit

0 \oplus x = x0⊕x=x

XOR of two equal bits (even if they are zeros) results in a zero

x \oplus x = 0x⊕x=0

and so on and so forth, i.e. one could see the bit in a bitmask only if it appears odd number of times.

fig

That's already great, so one could detect the bit which appears once, and the bit which appears three times. The problem is to distinguish between these two situations.

AND and NOT

To separate number that appears once from a number that appears three times let's use two bitmasks instead of one: seen_once and seen_twice.

The idea is to

change seen_once only if seen_twice is unchanged

change seen_twice only if seen_once is unchanged

fig

This way bitmask seen_once will keep only the number which appears once and not the numbers which appear three times.

Implementation


Complexity Analysis

Time complexity : \mathcal{O}(N)O(N) to iterate over the input array.

Space complexity : \mathcal{O}(1)O(1) since no additional data structures are allocated.

*/

class Solution {
    public int singleNumber(int[] digits) {
           Map<Integer,Integer> map =new HashMap<Integer,Integer>();
            for(int i=0;i<digits.length;i++){
               if(map.containsKey(digits[i])){
                   map.put(digits[i],(map.get(digits[i]))+1);
               }
               else{
                   map.put(digits[i],1);
               }
            }
            for(Map.Entry<Integer,Integer> key : map.entrySet()){
                if(key.getValue()==1) return key.getKey();
            }
            return 0;
    }
}
