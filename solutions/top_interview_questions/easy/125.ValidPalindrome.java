// Efficeint Solution: Using methods of Character class
class Solution {
    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<j){
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }
            if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

// Comparing first and last elements
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]","");
        int i=0;
        int j=s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}


// Using substring and reverse of string builder
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]","");
        int n = s.length();
        if(n==0) return true;
        String reverse = s.substring((n+1)/2, n);
        StringBuilder sb=new StringBuilder(reverse);
        reverse = sb.reverse().toString();
        n = (n%2)==0 ? n : n-1;
        s = s.substring(0, n/2);
        return s.equals(reverse);
    }
}