from solution_base import Solution

class LC_392_IsSubsequence(Solution):
    # ✅ Version 1: Optimized with index pointer
    def isSubsequence_index(self, s: str, t: str) -> bool:
        idx = 0
        for c in t:
            if idx < len(s) and c == s[idx]:
                idx += 1
        return idx == len(s)

    # ✅ Version 2: Two-pointer variant
    def isSubsequence_two_pointers(self, s: str, t: str) -> bool:
        i = j = 0
        while i < len(s) and j < len(t):
            if s[i] == t[j]:
                i += 1
            j += 1
        return i == len(s)

    def run(self):
        print("\n--- Running LC 392: Is Subsequence ---")

        s, t = "abc", "ahbgdc"
        print(f"s = '{s}', t = '{t}'")
        print("Using index method:", self.isSubsequence_index(s, t))
        print("Using two-pointer method:", self.isSubsequence_two_pointers(s, t))

        s, t = "axc", "ahbgdc"
        print(f"\ns = '{s}', t = '{t}'")
        print("Using index method:", self.isSubsequence_index(s, t))
        print("Using two-pointer method:", self.isSubsequence_two_pointers(s, t))