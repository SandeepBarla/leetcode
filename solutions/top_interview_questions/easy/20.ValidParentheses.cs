// Using Dictionary
// T.C = O(n); n is length string s
// S.C = O(n); size of stack in worst case
public class Solution {
    public bool IsValid(string s) {
        Dictionary<char, char> dic = new Dictionary<char, char>(){
            {'(', ')'},
            {'[', ']'},
            {'{', '}'},
        };
        Stack<char> stack = new Stack<char>();

        foreach(char c in s){
            if(dic.ContainsKey(c)){
                stack.Push(c); // push the opening bracket into stack
            }else{
                if(stack.Count > 0 && dic[stack.Peek()] == c){
                    stack.Pop(); // pop the bracket if it matches with the closing bracket
                }else{
                    return false;
                }
            }
        }

        return stack.Count==0;
    }
}

// Without Using Dictionary
// T.C = O(n); n is length string s
// S.C = O(n); size of stack in worst case
public class Solution {
   public bool IsValid(string s) {
        if(s==string.Empty) return true;
        if(s.Length==1) return false;
        if(s[^1]=='(' || s[^1]=='{' || s[^1]=='[') return false;
        
        var stk = new Stack<char>();
        int i=0;
        while(i<s.Length){
            if(s[i]=='(' || s[i]=='{' || s[i]=='[')
                stk.Push(s[i]);
            else{
                if(stk.Count==0) return false;
                var c = stk.Pop();
                if(s[i]==')' && c!='(') return false;
                if(s[i]=='}' && c!='{') return false;
                if(s[i]==']' && c!='[') return false;
            }
            i++;
        }
        return stk.Count==0;
    }
}
