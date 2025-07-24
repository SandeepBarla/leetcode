package solutions.company_wise.goldman_sachs.medium;

import java.util.Arrays;

import common.ArrayUtils;
import common.Solution;

public class LC_443_StringCompression implements Solution {

  // 🔹 Approach 1: Brute Force (Using StringBuilder) O(N) space
  public int compressUsingStringBuilder(char[] chars) {
    int count = 1;
    StringBuilder sb = new StringBuilder();

    for (int i = 1; i < chars.length; i++) {
      if (chars[i] != chars[i - 1]) {
        sb.append(chars[i - 1]);
        if (count > 1) {
          sb.append(String.valueOf(count));
        }
        count = 1;
      } else {
        count++;
      }
    }

    sb.append(chars[chars.length - 1]);
    if (count > 1) {
      sb.append(String.valueOf(count));
    }

    // Copy back the compressed string to chars[]
    String s = sb.toString();
    for (int i = 0; i < s.length(); i++) {
      chars[i] = s.charAt(i);
    }

    return s.length();
  }

  // 🔹 Approach 2: Optimized In-Place Compression (O(1) Space)
  public int compressInPlace(char[] chars) {
    int index = 0; // Pointer to write compressed characters
    int i = 0; // Pointer to traverse input array

    while (i < chars.length) {
      char currentChar = chars[i];
      int count = 0;

      // Count consecutive occurrences of currentChar
      while (i < chars.length && chars[i] == currentChar) {
        count++;
        i++;
      }

      // Write character
      chars[index++] = currentChar;

      // Write count if greater than 1
      if (count > 1) {
        for (char c : String.valueOf(count).toCharArray()) {
          chars[index++] = c;
        }
      }
    }

    return index; // New length of compressed array
  }

  @Override
  public void run() {
    // 🔹 Test Case 1
    char[] chars1 = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
    ArrayUtils.printCharArray("Input", chars1);

    int newLength1 = compressUsingStringBuilder(chars1);
    System.out.println("Compressed Length (StringBuilder): " + newLength1);
    ArrayUtils.printCharArray("Compressed Output", Arrays.copyOf(chars1, newLength1));

    char[] chars2 = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
    int newLength2 = compressInPlace(chars2);
    System.out.println("Compressed Length (In-Place): " + newLength2);
    ArrayUtils.printCharArray("Compressed Output", Arrays.copyOf(chars2, newLength2));

    // 🔹 Test Case 2
    char[] chars3 = { 'a' };
    ArrayUtils.printCharArray("Input", chars3);

    int newLength3 = compressUsingStringBuilder(chars3);
    System.out.println("Compressed Length (StringBuilder): " + newLength3);
    ArrayUtils.printCharArray("Compressed Output", Arrays.copyOf(chars3, newLength3));

    char[] chars4 = { 'a' };
    int newLength4 = compressInPlace(chars4);
    System.out.println("Compressed Length (In-Place): " + newLength4);
    ArrayUtils.printCharArray("Compressed Output", Arrays.copyOf(chars4, newLength4));
  }
}