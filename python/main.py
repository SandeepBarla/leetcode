# Main entry point to run one LeetCode solution at a time

from medium.lc_811_subdomain_visit_count import LC_811_SubdomainVisitCount
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_811_SubdomainVisitCount()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()