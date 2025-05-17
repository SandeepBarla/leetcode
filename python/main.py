# Main entry point to run one LeetCode solution at a time

from medium.lc_97_interleaving_string import LC_97_InterleavingString
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_97_InterleavingString()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()