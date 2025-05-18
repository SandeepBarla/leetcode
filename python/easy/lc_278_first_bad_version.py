from solution_base import Solution

# Note: This simulates the external isBadVersion API for testing locally
def isBadVersion(version: int) -> bool:
    return version >= LC_278_FirstBadVersion.first_bad  # Simulated API

class LC_278_FirstBadVersion(Solution):
    def firstBadVersion(self, n: int) -> int:
        lo, hi = 1, n
        while lo < hi:
            mid = (lo + hi) // 2
            if isBadVersion(mid):
                hi = mid  # First bad could be mid or earlier
            else:
                lo = mid + 1  # Bad version must be after mid
        return lo

    def run(self):
        n = 10
        LC_278_FirstBadVersion.first_bad = 7  # Simulated first bad version

        print("\nTotal Versions:", n)
        print("First Bad Version:", self.firstBadVersion(n))