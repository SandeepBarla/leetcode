from solution_base import Solution
from typing import List

class LC_39_CombinationSum(Solution):
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        dp = [[] for _ in range(target + 1)]
        dp[0] = [[]]  # One way to make sum 0 is empty list

        for candidate in candidates:
            for t in range(candidate, target + 1):
                for comb in dp[t - candidate]:
                    dp[t].append(comb + [candidate])

        return dp[target]

    def run(self):
        candidates = [2, 3, 6, 7]
        target = 7
        print("Input: candidates =", candidates, ", target =", target)
        print("Combinations:", self.combinationSum(candidates, target))

        candidates = [2, 3, 5]
        target = 8
        print("\nInput: candidates =", candidates, ", target =", target)
        print("Combinations:", self.combinationSum(candidates, target))