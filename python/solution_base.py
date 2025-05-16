# Base class for all Python LeetCode solutions
# Place in: leetcode/python/solution_base.py

from abc import ABC, abstractmethod

class Solution(ABC):
    @abstractmethod
    def run(self):
        """
        Abstract method to execute the solution with sample test cases.
        Override this in each problem file to verify your implementation.
        """
        pass