/*
 * LeetCode Problem 271: Encode and Decode Strings
 * URL: https://leetcode.com/problems/encode-and-decode-strings/
 * Difficulty: Medium
 *
 * Approach:
 * - Encode: For each string, append its length followed by '#' and then the string itself.
 *   Example: ["abc", "de"] → "3#abc2#de"
 * - Decode: Read length before each '#', then extract the corresponding substring.
 * - Handles empty strings and strings with special characters since '#' is not used inside strings.
 *
 * Time Complexity:
 *   - Encode: O(n) where n is the total length of all strings combined
 *   - Decode: O(n) same as above
 *
 * Space Complexity: O(n) for both encode and decode
 */

package solutions.neetcode_150.medium;

import java.util.ArrayList;
import java.util.List;

import common.Solution;

public class LC_271_EncodeDecodeStrings implements Solution {

  public static class Codec {

    // Encodes a list of strings to a single string
    public String encode(List<String> strs) {
      StringBuilder sb = new StringBuilder();
      for (String s : strs) {
        sb.append(s.length()).append('#').append(s);
      }
      return sb.toString();
    }

    // Decodes a single string to a list of strings
    public List<String> decode(String str) {
      List<String> result = new ArrayList<>();
      int i = 0;

      while (i < str.length()) {
        int length = 0;

        // Extract the length prefix
        while (str.charAt(i) != '#') {
          length = length * 10 + (str.charAt(i) - '0');
          i++;
        }

        i++; // Skip the '#'

        // Extract the substring of length `length`
        result.add(str.substring(i, i + length));
        i += length;
      }

      return result;
    }
  }

  @Override
  public void run() {
    Codec codec = new Codec();

    List<String> input = List.of("hello", "world", "", "leet#code", "123");
    String encoded = codec.encode(input);
    System.out.println("Encoded: " + encoded);

    List<String> decoded = codec.decode(encoded);
    System.out.println("Decoded: " + decoded); // Expected: original input list
  }
}