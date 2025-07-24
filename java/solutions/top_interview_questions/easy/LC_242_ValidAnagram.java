package solutions.top_interview_questions.easy;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_242_ValidAnagram implements Solution {

    // ✅ Approach 1: Using Bucket Array (O(n) time, O(1) space)
    public boolean isAnagramBucketArray(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] arr = new int[26];

        // Count frequencies from string s
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        // Subtract frequencies from string t
        for (int i = 0; i < t.length(); i++) {
            if (arr[t.charAt(i) - 'a'] == 0)
                return false; // If character not found
            arr[t.charAt(i) - 'a']--;
        }
        return true;
    }

    // ✅ Approach 2: Using HashMap (O(n) time, O(1) space)
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();

        // Count character frequencies from string s
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Subtract character frequencies from string t
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c) || map.get(c) == 0)
                return false;
            map.put(c, map.get(c) - 1);
        }

        return true;
    }

    // ✅ Approach 3: Using HashMap (Alternative Version)
    public boolean isAnagramHashMapAlternative(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0)
                return false;
            map.put(c, map.get(c) - 1);
        }

        return true;
    }

    @Override
    public void run() {
        // 🔹 Test Case 1: Valid Anagram
        String s1 = "anagram", t1 = "nagaram";
        System.out.println("Input: s = " + s1 + ", t = " + t1);
        System.out.println("Using Bucket Array: " + isAnagramBucketArray(s1, t1)); // Expected: true
        System.out.println("Using HashMap: " + isAnagramHashMap(s1, t1)); // Expected: true

        // 🔹 Test Case 2: Not an Anagram
        String s2 = "rat", t2 = "car";
        System.out.println("\nInput: s = " + s2 + ", t = " + t2);
        System.out.println("Using Bucket Array: " + isAnagramBucketArray(s2, t2)); // Expected: false
        System.out.println("Using HashMap: " + isAnagramHashMap(s2, t2)); // Expected: false

        // 🔹 Test Case 3: Edge Case - Empty Strings
        String s3 = "", t3 = "";
        System.out.println("\nInput: s = " + s3 + ", t = " + t3);
        System.out.println("Using Bucket Array: " + isAnagramBucketArray(s3, t3)); // Expected: true
        System.out.println("Using HashMap: " + isAnagramHashMap(s3, t3)); // Expected: true
    }
}