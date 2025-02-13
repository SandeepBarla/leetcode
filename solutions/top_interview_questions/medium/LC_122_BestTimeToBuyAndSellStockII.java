package solutions.top_interview_questions.medium;

import common.ArrayUtils;
import common.Solution;

public class LC_122_BestTimeToBuyAndSellStockII implements Solution {

  // Optimized Greedy Approach (O(n) Time | O(1) Space)
  public int maxProfitOptimized(int[] prices) {
    int totalProfit = 0;

    for (int i = 1; i < prices.length; i++) {
      // If price increased, add to profit
      if (prices[i] > prices[i - 1]) {
        totalProfit += prices[i] - prices[i - 1];
      }
    }
    return totalProfit;
  }

  // Original Approach (Fixed Issues) - Uses Buy & Sell Tracking (O(n) Time | O(1)
  // Space)
  public int maxProfitUsingBuySellTracking(int[] prices) {
    int buyPrice = prices[0];
    int sellPrice = prices[0];
    int totalProfit = 0;

    for (int i = 1; i < prices.length; i++) {
      int currProfit = sellPrice - buyPrice;

      // If a new dip is found, finalize previous profit and reset buy/sell
      if (prices[i] <= buyPrice || prices[i] <= sellPrice) {
        totalProfit += currProfit;
        buyPrice = prices[i];
        sellPrice = prices[i];
      }
      // If price is increasing, update sell price
      else if (prices[i] >= sellPrice) {
        sellPrice = prices[i];
      }
    }
    totalProfit += sellPrice - buyPrice; // Add last recorded profit
    return totalProfit;
  }

  @Override
  public void run() {
    int[][] testCases = {
        { 7, 1, 5, 3, 6, 4 }, // Expected: 7
        { 1, 2, 3, 4, 5 }, // Expected: 4
        { 5, 4, 3, 2, 1 }, // Expected: 0
        { 2, 1, 4, 5, 2, 9, 7 } // Expected: 10
    };

    for (int[] prices : testCases) {
      ArrayUtils.printArray("Stock Prices", prices);
      System.out.println("Optimized Approach Profit: " + maxProfitOptimized(prices));
      System.out.println("Buy-Sell Tracking Approach Profit: " + maxProfitUsingBuySellTracking(prices));
      System.out.println();
    }
  }
}