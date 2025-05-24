# Main entry point to run one LeetCode solution at a time


from easy.lc_977_sorted_squares import LC_977_SortedSquares
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_977_SortedSquares()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()