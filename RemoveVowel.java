/*
Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.

 

Example 1:

Input: s = "leetcodeisacommunityforcoders"
Output: "ltcdscmmntyfrcdrs"
Example 2:

Input: s = "aeiou"
Output: ""
 

Constraints:

1 <= s.length <= 1000
s consists of only lowercase English letters.
*/

class Solution {
    public String removeVowels(String s) {
       int length=s.length();
        if (length <= 0) {
            return "";
        }
        String output="";
        for(int i=0;i<s.length();i++){
            char a=s.charAt(i);
            //check if not a vowel then add to the output string
            if(!(a=='a'||a=='e'||a=='i'||a=='o'||a=='u')){
                output=output+a;
            }
        }

      return output;
  
    }
}
