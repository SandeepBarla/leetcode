from solution_base import Solution
from typing import List

class LC_977_SortedSquares(Solution):
    # ✅ Version 1: Interview-accepted, uses reverse()
    def sortedSquares_reverse(self, nums: List[int]) -> List[int]:
        left, right = 0, len(nums) - 1
        result = []

        while left <= right:
            left_squared = nums[left] ** 2
            right_squared = nums[right] ** 2
            if left_squared > right_squared:
                result.append(left_squared)
                left += 1
            else:
                result.append(right_squared)
                right -= 1

        result.reverse()
        return result

    # ✅ Version 2: Same logic, avoids reverse() by filling from back
    def sortedSquares_fill_backwards(self, nums: List[int]) -> List[int]:
        left, right = 0, len(nums) - 1
        result = [0] * len(nums)
        pos = len(nums) - 1

        while left <= right:
            left_squared = nums[left] ** 2
            right_squared = nums[right] ** 2
            if left_squared > right_squared:
                result[pos] = left_squared
                left += 1
            else:
                result[pos] = right_squared
                right -= 1
            pos -= 1

        return result

    def run(self):
        print("\n--- Running LC 977: Squares of a Sorted Array ---")
        nums1 = [-4, -1, 0, 3, 10]
        nums2 = [-7, -3, 2, 3, 11]

        for nums in [nums1, nums2]:
            print(f"\nInput: {nums}")
            print("Using reverse():", self.sortedSquares_reverse(nums[:]))
            print("Fill from back :", self.sortedSquares_fill_backwards(nums[:]))