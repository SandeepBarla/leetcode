from solution_base import Solution
from typing import List
from collections import defaultdict

class LC_811_SubdomainVisitCount(Solution):
    def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
        freq = defaultdict(int)
        for cpdomain in cpdomains:
            count, domain = cpdomain.split(' ')
            count = int(count)
            subdomains = domain.split('.')
            for i in range(len(subdomains)):
                key = '.'.join(subdomains[i:])
                freq[key] += count

        return [f"{v} {k}" for k, v in freq.items()]

    def run(self):
        print("\n--- Running LC 811: Subdomain Visit Count ---")

        cpdomains = ["9001 discuss.leetcode.com"]
        print("Input:", cpdomains)
        print("Output:", self.subdomainVisits(cpdomains))

        cpdomains = ["100 mail.google.com", "50 google.com", "1 com"]
        print("\nInput:", cpdomains)
        print("Output:", self.subdomainVisits(cpdomains))