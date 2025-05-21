# Main entry point to run one LeetCode solution at a time

from easy.lc_746_min_cost_climbing_stairs import LC_746_MinCostClimbingStairs
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_746_MinCostClimbingStairs()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()