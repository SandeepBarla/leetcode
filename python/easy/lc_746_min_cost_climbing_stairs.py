from solution_base import Solution
from typing import List

class LC_746_MinCostClimbingStairs(Solution):
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        if n == 0:
            return 0
        if n == 1:
            return cost[0]

        prev2 = cost[0]  # Cost to reach step 0
        prev1 = cost[1]  # Cost to reach step 1

        for i in range(2, n):
            curr = cost[i] + min(prev1, prev2)  # Min cost to reach step i
            prev2 = prev1
            prev1 = curr

        return min(prev1, prev2)  # Min cost to reach top (beyond last step)

    def run(self):
        cost1 = [10, 15, 20]
        cost2 = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]

        print("\nInput:", cost1)
        print("Min Cost to Reach Top:", self.minCostClimbingStairs(cost1))

        print("\nInput:", cost2)
        print("Min Cost to Reach Top:", self.minCostClimbingStairs(cost2))