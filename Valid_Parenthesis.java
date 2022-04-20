/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
*/
  

class Solution {
    public boolean isValid(String s) {
       
            int a=s.length();
            boolean flag=false;
            if(a==0)
                return false;
            if(a==1)
                return false;
            String sum ="";

            for (int i=0; i<a;i++){
                char c=s.charAt(i);
                if(c=='{' ||c=='['||c=='(' )
                {
                    sum=sum+c;
                }
                else if((c=='}' ||c==']'||c==')') && sum.length()==0 ){
                   return false; 
                }
                else if ((c=='}' && sum.charAt(sum.length()-1)=='{')||(c==']' && sum.charAt(sum.length()-1)=='[')||
                        (c==')' && sum.charAt(sum.length()-1)=='(') )
                {
                    sum=sum.substring(0,sum.length()-1);
                }
                else{
                    return false;
                }
            } 
        if(sum.length()==0)
            return true;
        else
            return false;
    }
}

