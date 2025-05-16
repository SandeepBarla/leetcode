# Main entry point to run one LeetCode solution at a time

from solution_base import Solution
from medium.lc_39_combination_sum import LC_39_CombinationSum  # ✅ Change this line

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_39_CombinationSum()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()