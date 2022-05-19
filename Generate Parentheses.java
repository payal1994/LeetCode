/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8

Solution: 

Approach 1: Brute Force
Intuition

We can generate all 2^{2n}2 
2n
  sequences of '(' and ')' characters. Then, we will check if each one is valid.

Algorithm

To generate all sequences, we use a recursion. All sequences of length n is just '(' plus all sequences of length n-1, and then ')' plus all sequences of length n-1.

To check whether a sequence is valid, we keep track of balance, the net number of opening brackets minus closing brackets. If it falls below zero at any time, or doesn't end in zero, the sequence is invalid - otherwise it is valid.


Complexity Analysis

Time Complexity : O(2^{2n}n)O(2 
2n
 n). For each of 2^{2n}2 
2n
  sequences, we need to create and validate the sequence, which takes O(n)O(n) work.

Space Complexity : O(2^{2n}n)O(2 
2n
 n). Naively, every sequence could be valid. See Approach 3 for development of a tighter asymptotic bound.


Approach 2: Backtracking
Intuition and Algorithm

Instead of adding '(' or ')' every time as in Approach 1, let's only add them when we know it will remain a valid sequence. We can do this by keeping track of the number of opening and closing brackets we have placed so far.

We can start an opening bracket if we still have one (of n) left to place. And we can start a closing bracket if it would not exceed the number of opening brackets.


Complexity Analysis

Our complexity analysis rests on understanding how many elements there are in generateParenthesis(n). This analysis is outside the scope of this article, but it turns out this is the n-th Catalan number \dfrac{1}{n+1}\binom{2n}{n} 
n+1
1
​
 ( 
n
2n
​
 ), which is bounded asymptotically by \dfrac{4^n}{n\sqrt{n}} 
n 
n
​
 
4 
n
 
​
 .

Time Complexity : O(\dfrac{4^n}{\sqrt{n}})O( 
n
​
 
4 
n
 
​
 ). Each valid sequence has at most n steps during the backtracking procedure.

Space Complexity : O(\dfrac{4^n}{\sqrt{n}})O( 
n
​
 
4 
n
 
​
 ), as described above, and using O(n)O(n) space to store the sequence.


Approach 3: Closure Number
Intuition

To enumerate something, generally we would like to express it as a sum of disjoint subsets that are easier to count.

Consider the closure number of a valid parentheses sequence S: the least index >= 0 so that S[0], S[1], ..., S[2*index+1] is valid. Clearly, every parentheses sequence has a unique closure number. We can try to enumerate them individually.

Algorithm

For each closure number c, we know the starting and ending brackets must be at index 0 and 2*c + 1. Then, the 2*c elements between must be a valid sequence, plus the rest of the elements must be a valid sequence.


Complexity Analysis

Time and Space Complexity : O(\dfrac{4^n}{\sqrt{n}})O( 
n
​
 
4 
n
 
​
 ). The analysis is similar to Approach 2.
 
 
 */
 class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<>();
        Parenthesis( ans,n,n,"");
        return ans;
    }
     public static void Parenthesis(ArrayList<String> ar, int o,
                                  int c, String output)
    {
            if(o==0 && c==0)
            {
                ar.add(output);
                return ;
            }

            if(o!=0){
                Parenthesis(ar,o-1,c,output+'(');
            }
            if(c>o){
                Parenthesis(ar,o,c-1,output+')');
            }
    }
}
