from solution_base import Solution

class LC_97_InterleavingString(Solution):
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        # Base check
        if len(s1) + len(s2) != len(s3):
            return False

        m, n = len(s1), len(s2)
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True

        for i in range(1, m + 1):
            dp[i][0] = dp[i - 1][0] and s1[i - 1] == s3[i - 1]
        for j in range(1, n + 1):
            dp[0][j] = dp[0][j - 1] and s2[j - 1] == s3[j - 1]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = (dp[i - 1][j] and s1[i - 1] == s3[i + j - 1]) or \
                           (dp[i][j - 1] and s2[j - 1] == s3[i + j - 1])

        return dp[m][n]

    def isInterleaveOptimized(self, s1: str, s2: str, s3: str) -> bool:
        if len(s1) + len(s2) != len(s3):
            return False

        if len(s2) > len(s1):
            s1, s2 = s2, s1  # Use shorter string for 1D space

        m, n = len(s1), len(s2)
        dp = [False] * (n + 1)
        dp[0] = True

        for j in range(1, n + 1):
            dp[j] = dp[j - 1] and s2[j - 1] == s3[j - 1]

        for i in range(1, m + 1):
            dp[0] = dp[0] and s1[i - 1] == s3[i - 1]
            for j in range(1, n + 1):
                dp[j] = (dp[j] and s1[i - 1] == s3[i + j - 1]) or \
                         (dp[j - 1] and s2[j - 1] == s3[i + j - 1])

        return dp[n]

    def run(self):
        s1 = "aabcc"
        s2 = "dbbca"
        s3 = "aadbbcbcac"

        print("\n-- 2D DP --")
        print("Input:", s1, s2, s3)
        print("Is Interleaving:", self.isInterleave(s1, s2, s3))

        print("\n-- 1D Optimized DP --")
        print("Input:", s1, s2, s3)
        print("Is Interleaving:", self.isInterleaveOptimized(s1, s2, s3))