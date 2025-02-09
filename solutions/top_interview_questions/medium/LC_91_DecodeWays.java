package solutions.top_interview_questions.medium;

import common.Solution;

public class LC_91_DecodeWays implements Solution {

  // Optimized Dynamic Programming solution
  // T.C = O(n)
  // S.C = O(1)
  public int numDecodings(String s) {
    if (s.charAt(0) == '0')
      return 0;

    int prev2 = 1, prev1 = 1;

    for (int i = 1; i < s.length(); i++) {
      int curr = 0;
      int oneDigit = Integer.parseInt(s.substring(i, i + 1));
      int twoDigits = Integer.parseInt(s.substring(i - 1, i + 1));

      if (oneDigit >= 1 && oneDigit <= 9)
        curr = prev1;
      if (twoDigits >= 10 && twoDigits <= 26)
        curr += prev2;

      prev2 = prev1;
      prev1 = curr;
    }
    return prev1;
  }

  @Override
  public void run() {
    String[] testCases = { "226", "12", "06", "2101" };

    for (String s : testCases) {
      System.out.println("Input String: " + s);
      System.out.println("Number of Decodings: " + numDecodings(s));
      System.out.println("-----------------------------");
    }
  }
}