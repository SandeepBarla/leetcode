# Main entry point to run one LeetCode solution at a time

from medium.lc_442_find_duplicates import LC_442_FindDuplicates
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_442_FindDuplicates()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()