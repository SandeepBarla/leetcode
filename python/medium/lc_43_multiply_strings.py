from solution_base import Solution

class LC_43_MultiplyStrings(Solution):
    def multiply(self, num1: str, num2: str) -> str:
        if num1 == '0' or num2 == '0':
            return '0'

        res = [0] * (len(num1) + len(num2))

        num1 = num1[::-1]
        num2 = num2[::-1]

        for i in range(len(num1)):
            d1 = int(num1[i])
            for j in range(len(num2)):
                d2 = int(num2[j])
                product = d1 * d2

                res[i + j] += product
                res[i + j + 1] += res[i + j] // 10
                res[i + j] %= 10

        while res and res[-1] == 0:
            res.pop()

        return ''.join(map(str, res[::-1]))

    def run(self):
        print("\n--- Running Multiply Strings ---")
        num1 = "123"
        num2 = "456"
        print("Input:", num1, "*", num2)
        print("Output:", self.multiply(num1, num2))