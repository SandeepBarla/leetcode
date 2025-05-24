# Main entry point to run one LeetCode solution at a time


from easy.lc_349_intersection_of_two_arrays import LC_349_IntersectionOfTwoArrays
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_349_IntersectionOfTwoArrays()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()