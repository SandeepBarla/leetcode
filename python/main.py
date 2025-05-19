# Main entry point to run one LeetCode solution at a time

from medium.lc_43_multiply_strings import LC_43_MultiplyStrings
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_43_MultiplyStrings()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()