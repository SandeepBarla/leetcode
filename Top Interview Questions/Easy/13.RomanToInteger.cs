public class Solution {
    public int RomanToInt(string s) {
        // Dictionary<char, int> dic = new Dictionary<char, int>();
        // dic['I'] = 1;
        // dic['V'] = 5;
        // dic['X'] = 10;
        // dic['L'] = 50;
        // dic['C'] = 100;
        // dic['D'] = 500;
        // dic['M'] = 1000;

        // int res = 0;

        // for(int i=0; i<s.Length; i++){
        //     if(i == s.Length-1) return res+dic[s[s.Length-1]];

        //     if(dic[s[i]] < dic[s[i+1]]){
        //         res = res + dic[s[i+1]] - dic[s[i]];
        //         i++;
        //     }else if(dic[s[i]] >= dic[s[i+1]]){
        //         res = res + dic[s[i]];
        //     }
        // }

        // return res;
        int res = 0;
        int n = s.Length;
        for(int i=0; i<n; i++){
            switch(s[i]){
                case 'I':
                    if(i==n-1) return res+1;
                    else{
                        if(s[i+1] == 'V'){
                            res = res+4;
                            i++;
                        }
                        else if(s[i+1] == 'X'){
                            res = res+9;
                            i++;
                        }
                        else res = res+1;
                    }
                    break;
                case 'X':
                    if(i==n-1) return res+10;
                    else{
                        if(s[i+1] == 'L'){
                            res = res+40;
                            i++;
                        }
                        else if(s[i+1] == 'C'){
                            res = res+90;
                            i++;
                        }
                        else res = res+10;
                    }
                    break;
                case 'C':
                    if(i==n-1) return res+100;
                    else{
                        if(s[i+1] == 'D'){
                            res = res+400;
                            i++;
                        }
                        else if(s[i+1] == 'M'){
                            res = res+900;
                            i++;
                        }
                        else res = res+100;
                    }
                    break;
                case 'V': res = res+5;break;
                case 'L': res = res+50;break;
                case 'D': res = res+500;break;
                case 'M': res = res+1000;break;
            }
        }

        return res;
    }
}