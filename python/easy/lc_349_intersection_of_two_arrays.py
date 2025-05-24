from solution_base import Solution
from typing import List

class LC_349_IntersectionOfTwoArrays(Solution):
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        set1 = set(nums1)
        set2 = set(nums2)
        return list(set1.intersection(set2))

    def run(self):
        print("\n--- Running Intersection of Two Arrays ---")
        
        nums1 = [1, 2, 2, 1]
        nums2 = [2, 2]
        print("Input:", nums1, nums2)
        print("Output:", self.intersection(nums1, nums2))

        nums1 = [4, 9, 5]
        nums2 = [9, 4, 9, 8, 4]
        print("\nInput:", nums1, nums2)
        print("Output:", self.intersection(nums1, nums2))