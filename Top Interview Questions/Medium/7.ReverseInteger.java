// Using long or double
class Solution {
    public int reverse(int x) {
        long reverse = 0; // or you can use double as well
        while(x!=0){
            reverse = reverse*10 + x%10;
            x/=10;
        }
        if(reverse>Integer.MAX_VALUE || reverse<Integer.MIN_VALUE) return 0;
        return (int)reverse;
    }
}

// using string builder
class Solution {
    public int reverse(int x) {
        StringBuilder result = new StringBuilder();
        String input = String.valueOf(x);
        for(int i=input.length()-1; i>=0; i--){
            result.append(input.charAt(i));
        }
        String res = result.toString();
        if(x<0) res = res.substring(0, res.length()-1);
        double d = Double.valueOf(res);
        if(d>Integer.MAX_VALUE || d<Integer.MIN_VALUE) return 0;
        return x<0 ? -1*(int)d : (int)d;
    }
}