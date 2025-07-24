package solutions.top_interview_questions.medium;

import java.util.HashMap;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;

public class LC_75_SortColors implements Solution {

  // Approach 1: Using HashMap (O(n) time, O(3) ~ O(1) space)
  public void sortColorsUsingHashMap(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    for (int i = 0; i < nums.length; i++) {
      if (map.getOrDefault(0, 0) > 0) {
        nums[i] = 0;
        map.put(0, map.get(0) - 1);
      } else if (map.getOrDefault(1, 0) > 0) {
        nums[i] = 1;
        map.put(1, map.get(1) - 1);
      } else if (map.getOrDefault(2, 0) > 0) {
        nums[i] = 2;
        map.put(2, map.get(2) - 1);
      }
    }
  }

  // Approach 2: Using Counting Sort (O(n) time, O(1) space)
  public void sortColorsUsingCount(int[] nums) {
    int zeros = 0, ones = 0, twos = 0;
    for (int i : nums) {
      switch (i) {
        case 0:
          zeros++;
          break;
        case 1:
          ones++;
          break;
        case 2:
          twos++;
          break;
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (zeros > 0) {
        nums[i] = 0;
        zeros--;
      } else if (ones > 0) {
        nums[i] = 1;
        ones--;
      } else if (twos > 0) {
        nums[i] = 2;
        twos--;
      }
    }
  }

  // Approach 3: Optimal (Dutch National Flag Algorithm, O(n) time, O(1) space)
  public void sortColorsInPlace(int[] nums) {
    int start = 0, mid = 0, end = nums.length - 1;
    while (mid <= end) {
      if (nums[mid] == 0) {
        swap(nums, start, mid);
        start++;
        mid++;
      } else if (nums[mid] == 1) {
        mid++;
      } else {
        swap(nums, end, mid);
        end--;
      }
    }
  }

  // Swap helper function
  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  @Override
  public void run() {
    int[] nums1 = { 2, 0, 2, 1, 1, 0 };
    int[] nums2 = nums1.clone();
    int[] nums3 = nums1.clone();

    System.out.println("Original Array:");
    ArrayUtils.printArray("Input", nums1);

    // Running all three approaches
    sortColorsUsingHashMap(nums1);
    System.out.println("After HashMap Sort:");
    ArrayUtils.printArray("Output (HashMap)", nums1);

    sortColorsUsingCount(nums2);
    System.out.println("After Counting Sort:");
    ArrayUtils.printArray("Output (Counting Sort)", nums2);

    sortColorsInPlace(nums3);
    System.out.println("After In-Place Sort:");
    ArrayUtils.printArray("Output (Dutch National Flag)", nums3);
  }
}