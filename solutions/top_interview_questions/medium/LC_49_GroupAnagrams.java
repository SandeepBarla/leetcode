package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ArrayUtils;
import common.Solution;

public class LC_49_GroupAnagrams implements Solution {

    // ✅ Approach 1: HashMap + Character Frequency Count as Key (O(n * k) time, O(n
    // * k) space)
    public List<List<String>> groupAnagramsUsingCount(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        // Iterate through all the strings
        for (String str : strs) {
            int[] charCount = new int[26]; // Frequency array for a-z
            for (char c : str.toCharArray()) {
                charCount[c - 'a']++; // Increment count
            }

            // Convert count array to a unique string key
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : charCount) {
                keyBuilder.append(count).append(",");
            }
            String key = keyBuilder.toString();

            // Add to corresponding anagram group
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    // ✅ Approach 2: HashMap + Sorted String as Key (O(n * k log k) time, O(n * k)
    // space)
    public List<List<String>> groupAnagramsUsingSorting(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    @Override
    public void run() {
        // 🔹 Test Case 1: Basic Example
        String[] strs1 = { "eat", "tea", "tan", "ate", "nat", "bat" };
        ArrayUtils.printStringArray("Input", strs1);
        List<List<String>> result1 = groupAnagramsUsingCount(strs1);
        ArrayUtils.print2DStringList("Grouped Anagrams (Optimized Count Method)", result1);

        // 🔹 Test Case 2: Sorting Method Example
        String[] strs2 = { "abc", "bca", "cab", "xyz", "zyx" };
        ArrayUtils.printStringArray("\nInput", strs2);
        List<List<String>> result2 = groupAnagramsUsingSorting(strs2);
        ArrayUtils.print2DStringList("Grouped Anagrams (Sorting Method)", result2);
    }
}