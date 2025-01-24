// Using if else conditions
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for(int i=1; i<=n; i++){
            res.add(i%3==0 ? (i%5==0 ? "FizzBuzz" : "Fizz") : (i%5==0 ? "Buzz" : String.valueOf(i)));
            if(i%3==0 && i%5==0) res.add("FizzBuzz");
            else if(i%3==0) res.add("Fizz");
            else if(i%5==0) res.add("Buzz");
            else res.add(i+""); // Integer.toString(i) or String.valueOf(i)
        }
        return res; 
    }
}

// Using switch statements
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for(int i=1; i<=n; i++){
            res.add(i%3==0 ? (i%5==0 ? "FizzBuzz" : "Fizz") : (i%5==0 ? "Buzz" : String.valueOf(i)));
        }
        return res;
    }
}