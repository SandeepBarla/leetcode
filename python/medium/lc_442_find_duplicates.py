from solution_base import Solution
from typing import List

class LC_442_FindDuplicates(Solution):
    def findDuplicates(self, nums: List[int]) -> List[int]:
        res = []
        for i in range(len(nums)):
            num = abs(nums[i])
            index = num - 1
            if nums[index] < 0:
                res.append(num)
            else:
                nums[index] *= -1
        return res

    def run(self):
        print("\n--- Running LC 442: Find All Duplicates in an Array ---")

        nums = [4, 3, 2, 7, 8, 2, 3, 1]
        print("Input:", nums)
        output = self.findDuplicates(nums[:])  # avoid mutation
        print("Output:", output)

        nums = [1, 1, 2]
        print("\nInput:", nums)
        output = self.findDuplicates(nums[:])
        print("Output:", output)

        nums = [1]
        print("\nInput:", nums)
        output = self.findDuplicates(nums[:])
        print("Output:", output)