from solution_base import Solution
from typing import List

class LC_322_CoinChange(Solution):
    def coinChange(self, coins: List[int], amount: int) -> int:
        # Initialize dp array where dp[i] holds the min number of coins to make amount i
        dp = [float('inf')] * (amount + 1)
        dp[0] = 0  # Base case: 0 coins needed to make amount 0

        for coin in coins:
            for target in range(coin, amount + 1):
                # If we can make amount (target - coin), try adding one more coin
                dp[target] = min(dp[target], dp[target - coin] + 1)

        # If dp[amount] is still inf, we can't make that amount with given coins
        return dp[amount] if dp[amount] != float('inf') else -1

    def run(self):
        print("\n--- Running LC 322: Coin Change ---")

        coins = [1, 2, 5]
        amount = 11
        print(f"Coins: {coins}, Amount: {amount}")
        print("Output:", self.coinChange(coins, amount))

        coins = [2]
        amount = 3
        print(f"\nCoins: {coins}, Amount: {amount}")
        print("Output:", self.coinChange(coins, amount))

        coins = [1]
        amount = 0
        print(f"\nCoins: {coins}, Amount: {amount}")
        print("Output:", self.coinChange(coins, amount))

        coins = [1]
        amount = 2
        print(f"\nCoins: {coins}, Amount: {amount}")
        print("Output:", self.coinChange(coins, amount))