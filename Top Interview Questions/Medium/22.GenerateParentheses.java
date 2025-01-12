// Backtracking approach
// T.C = O(4ⁿ / √n); Catalan number
// S.C = O(4ⁿ / √n); total combinations stored in result list
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, n, n, new StringBuilder(), res);
        return res;
    }

    private void backtrack(int open, int close, int n, StringBuilder comb, List<String> res){
        // when we are left with no more paranthesis, return the combination string 
        if(comb.length()==2*n){
            res.add(comb.toString());
            return;
        }
        // see if you are left with any open brackets
        if(open>0){
            comb.append('(');
            backtrack(open-1, close, n, comb, res);
            comb.deleteCharAt(comb.length()-1);
        }
        // see if there is an imbalance in the paranthesis
        if(close>open){
            comb.append(')');
            backtrack(open, close-1, n, comb, res);
            comb.deleteCharAt(comb.length()-1);
        }
    }
}