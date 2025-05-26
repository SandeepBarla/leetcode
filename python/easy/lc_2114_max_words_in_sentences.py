from solution_base import Solution
from typing import List

class LC_2114_MaxWordsInSentences(Solution):
    # ✅ Version 1: Count spaces and add 1 (faster, less robust)
    def mostWordsFound_by_counting_spaces(self, sentences: List[str]) -> int:
        # Assumes each word is separated by a single space and no trailing spaces exist
        return max(sentence.count(' ') for sentence in sentences) + 1

    # ✅ Version 2: Use split to count words (robust and preferred in interviews)
    def mostWordsFound_by_split(self, sentences: List[str]) -> int:
        return max(len(sentence.split()) for sentence in sentences)

    def run(self):
        print("\n--- Running LC 2114: Maximum Number of Words Found in Sentences ---")

        sentences = [
            "Alice and Bob love LeetCode",
            "I think so too",
            "This is great thanks very much"
        ]

        print("Input:", sentences)
        print("By counting spaces:", self.mostWordsFound_by_counting_spaces(sentences))
        print("By split():", self.mostWordsFound_by_split(sentences))