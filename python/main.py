# Main entry point to run one LeetCode solution at a time

from medium.lc_451_sort_characters_by_frequency import LC_451_SortCharactersByFrequency
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_451_SortCharactersByFrequency()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()