# Main entry point to run one LeetCode solution at a time
import sys; sys.dont_write_bytecode = True
from medium.lc_322_coin_change import LC_322_CoinChange
from solution_base import Solution


if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_322_CoinChange()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()