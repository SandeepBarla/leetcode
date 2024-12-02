public class Solution {
    public IList<IList<int>> Generate(int numRows) {
        int[][] res = new int[numRows][];

        for(int i=0; i<numRows; i++){
            res[i] = new int[i+1]; // every row has one element greater than the previous one
            res[i][0] = 1; // initiate first and last elements of a row
            res[i][i] = 1;
            for(int j=1; j<i; j++){
                res[i][j] = res[i-1][j-1] + res[i-1][j]; // calculate the value using previous row
            }
        }
        return res;
    }
}