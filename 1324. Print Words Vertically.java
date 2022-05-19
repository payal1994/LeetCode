/*

Given a string s. Return all the words vertically in the same order in which they appear in s.
Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
Each word would be put on only one column and that in one column there will be only one word.

 

Example 1:

Input: s = "HOW ARE YOU"
Output: ["HAY","ORO","WEU"]
Explanation: Each word is printed vertically. 
 "HAY"
 "ORO"
 "WEU"
Example 2:

Input: s = "TO BE OR NOT TO BE"
Output: ["TBONTB","OEROOE","   T"]
Explanation: Trailing spaces is not allowed. 
"TBONTB"
"OEROOE"
"   T"
Example 3:

Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 

Constraints:

1 <= s.length <= 200
s contains only upper case English letters.
It's guaranteed that there is only one space between 2 words.

*/

class Solution {
    public List<String> printVertically(String a) {
        ArrayList<String> al = new ArrayList<String>();
        String[] s = a.split(" ");
        int maxlength = 0;
        for (String i : s) {
            maxlength = Math.max(maxlength, i.length());
        }
        for (int i = 0; i < maxlength; i++) {
            String sum = "";
            for (int j = 0; j < s.length; j++) {
                char c;
                if (s[j].length()-1 < i) {
                    c = ' ';
                } else
                    c = s[j].charAt(i);
                sum = sum + c;
            }
            al.add(sum.replaceFirst("\\s++$",""));
        }
        return al;   
    }
}
