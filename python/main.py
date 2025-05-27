# Main entry point to run one LeetCode solution at a time

from easy.lc_392_is_subsequence import LC_392_IsSubsequence
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_392_IsSubsequence()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()