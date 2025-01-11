// Backtracking approach
// T.C = O(4^n); n is length of the string digits;
// S.C = O(4^n * n); extra space for ouput list; n for recursion call stack
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.equals("")) return res;
        HashMap<Integer, String> map = new HashMap<>();
        map.put(2,"abc");
        map.put(3,"def");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");
        backtrack(0, digits, map, new StringBuilder(), res);
        return res;
    }
    private void backtrack(int idx, String digits, Map<Integer, String> map, StringBuilder comb, List<String> res){
        // if the idx reaches end of the string, add the string to result array
        if(idx==digits.length()){
            res.add(comb.toString());
            return;
        }
        String str = map.get(digits.charAt(idx)-'0');
        // iterate through each character of the phonenumber
        for(char c: str.toCharArray()){
            comb.append(c); // append it to combination stringbuilder
            backtrack(idx+1, digits, map, comb, res); // perform backtrack for the next index
            comb.deleteCharAt(idx); // remove the character after backtrack
        }
    }
}


// Bruteforce approach
// T.C = O(n^x); x is length of the string digits; n is 3 or 4
// S.C = O(4^n * n); extra space for ouput list
class Solution {
    public List<String> letterCombinations(String digits) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(2,"abc");
        map.put(3,"def");
        map.put(4,"ghi");
        map.put(5,"jkl");
        map.put(6,"mno");
        map.put(7,"pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");
        List<String> res = new ArrayList<>();
        if(digits.length()>0){
            for(int i=0; i<map.get(digits.charAt(0)-'0').length(); i++){
                if(digits.length()>1){
                    for(int j=0; j<map.get(digits.charAt(1)-'0').length(); j++){
                        if(digits.length()>2){
                            for(int k=0; k<map.get(digits.charAt(2)-'0').length(); k++){
                                if(digits.length()>3){
                                    for(int l=0; l<map.get(digits.charAt(3)-'0').length(); l++){
                                        res.add(map.get(digits.charAt(0)-'0').charAt(i)+""+map.get(digits.charAt(1)-'0').charAt(j)+""+map.get(digits.charAt(2)-'0').charAt(k)+""+map.get(digits.charAt(3)-'0').charAt(l)+"");
                                    }
                                }else{
                                    res.add(map.get(digits.charAt(0)-'0').charAt(i)+""+map.get(digits.charAt(1)-'0').charAt(j)+""+map.get(digits.charAt(2)-'0').charAt(k)+"");
                                }
                            }
                        }else{
                           res.add(map.get(digits.charAt(0)-'0').charAt(i)+""+map.get(digits.charAt(1)-'0').charAt(j)+""); 
                        }
                    }
                }else{
                    res.add(map.get(digits.charAt(0)-'0').charAt(i)+"");
                }
            }
        }
        return res;
        
    }
}