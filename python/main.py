# Main entry point to run one LeetCode solution at a time

from easy.lc_2114_max_words_in_sentences import LC_2114_MaxWordsInSentences
from solution_base import Solution

if __name__ == "__main__":
    # ✅ Instantiate the solution you want to test
    solution: Solution = LC_2114_MaxWordsInSentences()  # ✅ Change this line

    print(f"\n--- Running {solution.__class__.__name__} ---")
    solution.run()