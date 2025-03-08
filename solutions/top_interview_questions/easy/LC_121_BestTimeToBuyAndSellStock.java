package solutions.top_interview_questions.easy;

import common.ArrayUtils;
import common.Solution;

public class LC_121_BestTimeToBuyAndSellStock implements Solution {

    // ✅ Optimized Single-Pass Approach (O(N) time, O(1) space)
    public int maxProfit(int[] prices) {
        int buyPrice = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buyPrice) {
                // Update the buyPrice if we find a lower price
                buyPrice = prices[i];
            } else {
                // Calculate the current profit and update the max profit
                int currentProfit = prices[i] - buyPrice;
                profit = Math.max(profit, currentProfit);
            }
        }
        return profit;
    }

    @Override
    public void run() {
        // 🔹 Test Case 1
        int[] prices1 = { 7, 1, 5, 3, 6, 4 };
        ArrayUtils.printArray("Stock Prices", prices1);
        System.out.println("Max Profit: " + maxProfit(prices1)); // Expected Output: 5

        // 🔹 Test Case 2
        int[] prices2 = { 7, 6, 4, 3, 1 };
        ArrayUtils.printArray("Stock Prices", prices2);
        System.out.println("Max Profit: " + maxProfit(prices2)); // Expected Output: 0

        // 🔹 Test Case 3
        int[] prices3 = { 2, 4, 1 };
        ArrayUtils.printArray("Stock Prices", prices3);
        System.out.println("Max Profit: " + maxProfit(prices3)); // Expected Output: 2
    }
}