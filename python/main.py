# Main entry point to run one LeetCode solution at a time

from easy.lc_278_first_bad_version import LC_278_FirstBadVersion
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_278_FirstBadVersion()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()