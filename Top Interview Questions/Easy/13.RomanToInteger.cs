// Using Dictionary
public class Solution {
    public int RomanToInt(string s) {
        Dictionary<char, int> dic = new Dictionary<char, int>();
        dic['I'] = 1;
        dic['V'] = 5;
        dic['X'] = 10;
        dic['L'] = 50;
        dic['C'] = 100;
        dic['D'] = 500;
        dic['M'] = 1000;

        int res = 0;

        for(int i=0; i<s.Length; i++){
            if(i == s.Length-1) return res+dic[s[s.Length-1]]; // add last value to result when i reaches end of the string

            if(dic[s[i]] < dic[s[i+1]]){ // if it is in ascending order
                res = res + dic[s[i+1]] - dic[s[i]]; // add the diff to result
                i++; // skip the next value by incrementing i value
            }else if(dic[s[i]] >= dic[s[i+1]]){
                res = res + dic[s[i]]; // else add the value to result
            }
        }

        return res;
    }
}

// Using pattern
public class Solution {
    public int RomanToInt(string s) {
        int res = 0;
        int n = s.Length;
        for(int i=0; i<n; i++){
            switch(s[i]){
                case 'I':
                    if(i==n-1) return res+1; // if I is last element just add it's value (1) to result
                    else{
                        if(s[i+1] == 'V'){ // if IV found, just add 4 and skip the next roman by incrementing i value
                            res = res+4;
                            i++;
                        }
                        else if(s[i+1] == 'X'){ // if IX found, just add 9 and skip the next roman by incrementing i value
                            res = res+9;
                            i++;
                        }
                        else res = res+1; // else just add the roman value to result
                    }
                    break;
                case 'X':
                    if(i==n-1) return res+10; // if X is last element just add it's value (10) to result
                    else{
                        if(s[i+1] == 'L'){ // if XL found, just add 40 and skip the next roman by incrementing i value
                            res = res+40;
                            i++;
                        }
                        else if(s[i+1] == 'C'){ // if XC found, just add 90 and skip the next roman by incrementing i value
                            res = res+90;
                            i++;
                        }
                        else res = res+10; // else just add the roman value to result
                    }
                    break;
                case 'C':
                    if(i==n-1) return res+100; // if C is last element just add it's value (100) to result
                    else{
                        if(s[i+1] == 'D'){ // if CD found, just add 400 and skip the next roman by incrementing i value
                            res = res+400;
                            i++;
                        }
                        else if(s[i+1] == 'M'){ // if CM found, just add 900 and skip the next roman by incrementing i value
                            res = res+900;
                            i++;
                        }
                        else res = res+100; // else just add the roman value to result
                    }
                    break;
                
                // for all other romans just add the values to the result
                case 'V': res = res+5;break;
                case 'L': res = res+50;break;
                case 'D': res = res+500;break;
                case 'M': res = res+1000;break;
            }
        }

        return res;
    }
}