from solution_base import Solution
from typing import List
from collections import Counter
import heapq

class LC_451_SortCharactersByFrequency(Solution):
    # ✅ Version 1: Sorting using Counter + sort()
    def frequencySort_sort(self, s: str) -> str:
        freq = Counter(s)
        items = list(freq.items())
        items.sort(key=lambda x: x[1], reverse=True)
        return ''.join([c * f for c, f in items])

    # ✅ Version 2: Max-Heap (Priority Queue)
    def frequencySort_heap(self, s: str) -> str:
        counter = Counter(s)
        max_heap = [(-freq, char) for char, freq in counter.items()]
        heapq.heapify(max_heap)

        result = []
        while max_heap:
            freq, char = heapq.heappop(max_heap)
            result.append(char * -freq)

        return ''.join(result)

    def run(self):
        print("\n--- Running LC 451: Sort Characters By Frequency ---")

        s = "tree"
        print("Input:", s)
        print("Sort version:", self.frequencySort_sort(s))
        print("Heap version:", self.frequencySort_heap(s))

        s = "cccaaa"
        print("\nInput:", s)
        print("Sort version:", self.frequencySort_sort(s))
        print("Heap version:", self.frequencySort_heap(s))

        s = "Aabb"
        print("\nInput:", s)
        print("Sort version:", self.frequencySort_sort(s))
        print("Heap version:", self.frequencySort_heap(s))