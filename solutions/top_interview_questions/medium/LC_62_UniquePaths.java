package solutions.top_interview_questions.medium;

import java.util.Arrays;

import common.Solution;

// T.C = O(m*n)
// S.C = O(n)

public class LC_62_UniquePaths implements Solution {

    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, 1);
        // iterate through all the rows
        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            curr[0] = 1;
            // iterate through all the cols
            for (int j = 1; j < n; j++) {
                // calculate number of unique paths to current cell
                // which is sum of unique paths to above and left cells
                curr[j] = prev[j] + curr[j - 1];
            }
            prev = curr;
        }
        return prev[n - 1];
    }

    @Override
    public void run() {
        int m = 3, n = 7;

        System.out.println("Grid Size: " + m + "x" + n);
        System.out.println("Unique Paths: " + uniquePaths(m, n));

        // Additional test case
        m = 3;
        n = 2;
        System.out.println("Grid Size: " + m + "x" + n);
        System.out.println("Unique Paths: " + uniquePaths(m, n));
    }
}