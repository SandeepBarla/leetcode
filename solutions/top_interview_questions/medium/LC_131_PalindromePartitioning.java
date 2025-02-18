package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.List;

import common.ArrayUtils;
import common.Solution;

public class LC_131_PalindromePartitioning implements Solution {

    // ✅ Approach 1: Backtracking with On-the-Fly Palindrome Checking (O(n × 2^n)
    // Time)
    public List<List<String>> partitionUsingBacktracking(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (isPalindrome(s, start, end - 1)) {
                path.add(s.substring(start, end)); // Add current partition
                backtrack(s, end, path, res); // Recursive call
                path.remove(path.size() - 1); // Backtrack step
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--))
                return false;
        }
        return true;
    }

    // ✅ Approach 2: Optimized Backtracking with Precomputed DP Table (O(n^2 × 2^n)
    // Time)
    public List<List<String>> partitionUsingDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // Step 1: Precompute all palindromic substrings using DP
        for (int len = 1; len <= n; len++) {
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                if (s.charAt(start) == s.charAt(end)) {
                    if (len == 1 || len == 2) {
                        dp[start][end] = true; // Single letter or two equal letters
                    } else {
                        dp[start][end] = dp[start + 1][end - 1]; // Expand palindrome
                    }
                }
            }
        }

        // Step 2: Use Backtracking with Precomputed DP Table
        List<List<String>> res = new ArrayList<>();
        backtrackUsingDP(s, 0, new ArrayList<>(), res, dp);
        return res;
    }

    private void backtrackUsingDP(String s, int start, List<String> path, List<List<String>> res, boolean[][] dp) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (dp[start][end]) { // Only continue if it's a palindrome
                path.add(s.substring(start, end + 1));
                backtrackUsingDP(s, end + 1, path, res, dp);
                path.remove(path.size() - 1);
            }
        }
    }

    @Override
    public void run() {
        String[] testCases = { "aab", "racecar", "abc", "banana" };

        for (String s : testCases) {
            System.out.println("Input String: " + s);
            System.out.println("Partitions using Backtracking:");
            ArrayUtils.print2DStringList("Result", partitionUsingBacktracking(s));

            System.out.println("\nPartitions using DP Optimization:");
            ArrayUtils.print2DStringList("Result", partitionUsingDP(s));
            System.out.println("--------------------------------------------------");
        }
    }
}