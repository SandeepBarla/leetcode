package solutions.neetcode_150.medium;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

/**
 * Leetcode 494: Target Sum
 *
 * Problem:
 * Given an integer array `nums` and an integer `target`,
 * return the number of different expressions you can build
 * by adding '+' or '-' in front of each number such that
 * the final expression evaluates to `target`.
 *
 * Constraints:
 * - All nums are non-negative
 * - You must use all the elements
 *
 * Solutions:
 * 1. Optimized DP using Subset Sum Conversion
 * 2. Brute-force DFS (for understanding)
 * 3. HashMap-based DP (Top-down tabulation)
 */
public class LC_494_TargetSum extends Solution {

    /**
     * ✅ Optimized DP using Subset Sum Transformation
     *
     * Convert to subset sum problem:
     *   sum(P) - sum(N) = target
     *   sum(P) + sum(N) = totalSum
     * → 2 * sum(P) = target + totalSum
     * → Subset sum = (target + totalSum) / 2
     *
     * T.C: O(n * subsetSum)
     * S.C: O(subsetSum)
     */
    public int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if ((totalSum + target) % 2 != 0 || Math.abs(target) > totalSum) return 0;

        int subsetSum = (totalSum + target) / 2;
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int i = subsetSum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }

        return dp[subsetSum];
    }

    /**
     * ✅ DFS Brute-force Approach (Exponential Time)
     *
     * Explore both '+' and '-' recursively for each index.
     *
     * T.C: O(2^n)
     * S.C: O(n) – recursion stack
     */
    public int findTargetSumWaysDFS(int[] nums, int target) {
        int[] res = new int[1];
        dfs(nums, 0, 0, target, res);
        return res[0];
    }

    private void dfs(int[] nums, int index, int currSum, int target, int[] res) {
        if (index == nums.length) {
            if (currSum == target) res[0]++;
            return;
        }

        dfs(nums, index + 1, currSum + nums[index], target, res); // Add
        dfs(nums, index + 1, currSum - nums[index], target, res); // Subtract
    }

    /**
     * ✅ HashMap-based Dynamic Programming (Top-down tabulation)
     *
     * For each num, create a new HashMap of (sum → count)
     * and update all possibilities from the previous step.
     *
     * T.C: O(n * range) where range is sum of all possible totals at each step
     * S.C: O(range)
     */
    public int findTargetSumWaysMapDP(int[] nums, int target) {
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1); // One way to form sum 0

        for (int num : nums) {
            Map<Integer, Integer> nextMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : sumCount.entrySet()) {
                int currSum = entry.getKey();
                int count = entry.getValue();

                nextMap.put(currSum + num, nextMap.getOrDefault(currSum + num, 0) + count);
                nextMap.put(currSum - num, nextMap.getOrDefault(currSum - num, 0) + count);
            }
            sumCount = nextMap;
        }

        return sumCount.getOrDefault(target, 0);
    }
}