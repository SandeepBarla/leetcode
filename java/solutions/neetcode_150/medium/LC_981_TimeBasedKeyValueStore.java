/*
 * LeetCode Problem: 981. Time Based Key-Value Store
 * URL: https://leetcode.com/problems/time-based-key-value-store/
 * Difficulty: Medium
 * 
 * Problem Description:
 * Design a time-based key-value data structure that can store multiple values for the same key 
 * at different time stamps and retrieve the key's value at a certain timestamp.
 * 
 * Implement the TimeMap class:
 * - TimeMap() Initializes the object of the data structure.
 * - void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * - String get(String key, int timestamp) Returns a value such that set was called previously, 
 *   with timestamp_prev <= timestamp. If there are multiple such values, it returns the value 
 *   associated with the largest timestamp_prev. If there are no such values, it returns "".
 * 
 * Example 1:
 * Input:
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output:
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 * 
 * Explanation:
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 * 
 * Constraints:
 * - 1 <= key.length, value.length <= 100
 * - key and value consist of lowercase English letters and digits.
 * - 1 <= timestamp <= 10^7
 * - All the timestamps timestamp of set are strictly increasing.
 * - At most 2 * 10^5 calls will be made to set and get.
 */

package solutions.neetcode_150.medium;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import common.Solution;

public class LC_981_TimeBasedKeyValueStore implements Solution {
    
    /**
     * Approach 1: HashMap + Binary Search with Custom Pair Class (Optimized Original)
     * 
     * Algorithm:
     * 1. Use HashMap to map keys to lists of (value, timestamp) pairs
     * 2. For set(): Add new pair to the list for the given key
     * 3. For get(): Binary search to find largest timestamp <= target
     * 4. Custom Pair class for clean data structure
     * 
     * Time Complexity: 
     * - set(): O(1) amortized
     * - get(): O(log n) where n is number of timestamps for the key
     * Space Complexity: O(m * n) where m is number of keys, n is average timestamps per key
     */
    static class TimeMapBinarySearch {
        private static class Pair {
            String value;
            int timestamp;
            
            public Pair(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }
        
        private Map<String, List<Pair>> map;
        
        public TimeMapBinarySearch() {
            map = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair(value, timestamp));
        }
        
        public String get(String key, int timestamp) {
            List<Pair> pairs = map.get(key);
            if (pairs == null || pairs.isEmpty()) {
                return "";
            }
            
            int left = 0;
            int right = pairs.size() - 1;
            String result = "";
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int currentTimestamp = pairs.get(mid).timestamp;
                
                if (currentTimestamp <= timestamp) {
                    result = pairs.get(mid).value;
                    left = mid + 1; // Look for larger timestamp
                } else {
                    right = mid - 1;
                }
            }
            
            return result;
        }
    }
    
    /**
     * Approach 2: TreeMap for Automatic Sorting
     * 
     * Algorithm:
     * 1. Use HashMap to map keys to TreeMaps (timestamp -> value)
     * 2. TreeMap automatically maintains sorted order of timestamps
     * 3. Use floorEntry() to find largest timestamp <= target
     * 
     * Time Complexity:
     * - set(): O(log n) due to TreeMap insertion
     * - get(): O(log n) due to TreeMap floorEntry
     * Space Complexity: O(m * n) where m is number of keys, n is average timestamps per key
     */
    static class TimeMapTreeMap {
        private Map<String, TreeMap<Integer, String>> map;
        
        public TimeMapTreeMap() {
            map = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }
        
        public String get(String key, int timestamp) {
            TreeMap<Integer, String> timeMap = map.get(key);
            if (timeMap == null) {
                return "";
            }
            
            Map.Entry<Integer, String> entry = timeMap.floorEntry(timestamp);
            return entry == null ? "" : entry.getValue();
        }
    }
    
    /**
     * Approach 3: Linear Search (Brute Force)
     * 
     * Algorithm:
     * 1. Use HashMap to map keys to lists of (value, timestamp) pairs
     * 2. For get(): Linear search through all timestamps to find best match
     * 3. Keep track of the best valid timestamp found so far
     * 
     * Time Complexity:
     * - set(): O(1) amortized
     * - get(): O(n) where n is number of timestamps for the key
     * Space Complexity: O(m * n) where m is number of keys, n is average timestamps per key
     * Note: For educational purposes; not recommended for large datasets
     */
    static class TimeMapLinear {
        private static class TimestampValue {
            String value;
            int timestamp;
            
            public TimestampValue(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }
        
        private Map<String, List<TimestampValue>> map;
        
        public TimeMapLinear() {
            map = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimestampValue(value, timestamp));
        }
        
        public String get(String key, int timestamp) {
            List<TimestampValue> values = map.get(key);
            if (values == null || values.isEmpty()) {
                return "";
            }
            
            String result = "";
            int bestTimestamp = -1;
            
            for (TimestampValue tv : values) {
                if (tv.timestamp <= timestamp && tv.timestamp > bestTimestamp) {
                    bestTimestamp = tv.timestamp;
                    result = tv.value;
                }
            }
            
            return result;
        }
    }
    
    /**
     * Approach 4: Optimized with Array of Arrays
     * 
     * Algorithm:
     * 1. Use HashMap to map keys to lists of int[2] arrays {timestamp, valueIndex}
     * 2. Separate list to store actual values to save memory
     * 3. Binary search on timestamp arrays for efficiency
     * 
     * Time Complexity:
     * - set(): O(1) amortized
     * - get(): O(log n) where n is number of timestamps for the key
     * Space Complexity: O(m * n) - potentially more memory efficient for repeated values
     */
    static class TimeMapArrayOptimized {
        private Map<String, List<int[]>> timestampMap; // [timestamp, valueIndex]
        private List<String> values;
        
        public TimeMapArrayOptimized() {
            timestampMap = new HashMap<>();
            values = new ArrayList<>();
        }
        
        public void set(String key, String value, int timestamp) {
            int valueIndex = values.size();
            values.add(value);
            timestampMap.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{timestamp, valueIndex});
        }
        
        public String get(String key, int timestamp) {
            List<int[]> timestamps = timestampMap.get(key);
            if (timestamps == null || timestamps.isEmpty()) {
                return "";
            }
            
            int left = 0;
            int right = timestamps.size() - 1;
            int bestIndex = -1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int currentTimestamp = timestamps.get(mid)[0];
                
                if (currentTimestamp <= timestamp) {
                    bestIndex = timestamps.get(mid)[1];
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            return bestIndex == -1 ? "" : values.get(bestIndex);
        }
    }
    
    @Override
    public void run() {
        System.out.println("Testing Time-Based Key-Value Store Implementations");
        System.out.println("=================================================");
        
        // Test Case 1: Basic functionality from problem example
        System.out.println("\nTest Case 1: Basic Example");
        testBasicFunctionality();
        
        // Test Case 2: Multiple keys
        System.out.println("\nTest Case 2: Multiple Keys");
        testMultipleKeys();
        
        // Test Case 3: Edge cases
        System.out.println("\nTest Case 3: Edge Cases");
        testEdgeCases();
        
        // Test Case 4: Large timestamps
        System.out.println("\nTest Case 4: Large Timestamps");
        testLargeTimestamps();
    }
    
    private void testBasicFunctionality() {
        System.out.println("Binary Search Approach:");
        TimeMapBinarySearch tm1 = new TimeMapBinarySearch();
        tm1.set("foo", "bar", 1);
        System.out.println("get('foo', 1): " + tm1.get("foo", 1)); // Expected: "bar"
        System.out.println("get('foo', 3): " + tm1.get("foo", 3)); // Expected: "bar"
        tm1.set("foo", "bar2", 4);
        System.out.println("get('foo', 4): " + tm1.get("foo", 4)); // Expected: "bar2"
        System.out.println("get('foo', 5): " + tm1.get("foo", 5)); // Expected: "bar2"
        
        System.out.println("\nTreeMap Approach:");
        TimeMapTreeMap tm2 = new TimeMapTreeMap();
        tm2.set("foo", "bar", 1);
        System.out.println("get('foo', 1): " + tm2.get("foo", 1)); // Expected: "bar"
        System.out.println("get('foo', 3): " + tm2.get("foo", 3)); // Expected: "bar"
        tm2.set("foo", "bar2", 4);
        System.out.println("get('foo', 4): " + tm2.get("foo", 4)); // Expected: "bar2"
        System.out.println("get('foo', 5): " + tm2.get("foo", 5)); // Expected: "bar2"
        
        System.out.println("\nLinear Search Approach:");
        TimeMapLinear tm3 = new TimeMapLinear();
        tm3.set("foo", "bar", 1);
        System.out.println("get('foo', 1): " + tm3.get("foo", 1)); // Expected: "bar"
        System.out.println("get('foo', 3): " + tm3.get("foo", 3)); // Expected: "bar"
        tm3.set("foo", "bar2", 4);
        System.out.println("get('foo', 4): " + tm3.get("foo", 4)); // Expected: "bar2"
        System.out.println("get('foo', 5): " + tm3.get("foo", 5)); // Expected: "bar2"
        
        System.out.println("\nArray Optimized Approach:");
        TimeMapArrayOptimized tm4 = new TimeMapArrayOptimized();
        tm4.set("foo", "bar", 1);
        System.out.println("get('foo', 1): " + tm4.get("foo", 1)); // Expected: "bar"
        System.out.println("get('foo', 3): " + tm4.get("foo", 3)); // Expected: "bar"
        tm4.set("foo", "bar2", 4);
        System.out.println("get('foo', 4): " + tm4.get("foo", 4)); // Expected: "bar2"
        System.out.println("get('foo', 5): " + tm4.get("foo", 5)); // Expected: "bar2"
    }
    
    private void testMultipleKeys() {
        TimeMapBinarySearch tm = new TimeMapBinarySearch();
        
        // Set values for multiple keys
        tm.set("love", "high", 10);
        tm.set("love", "low", 20);
        tm.set("hate", "anger", 15);
        tm.set("hate", "fury", 25);
        
        System.out.println("get('love', 5): " + tm.get("love", 5));   // Expected: ""
        System.out.println("get('love', 10): " + tm.get("love", 10)); // Expected: "high"
        System.out.println("get('love', 15): " + tm.get("love", 15)); // Expected: "high"
        System.out.println("get('love', 20): " + tm.get("love", 20)); // Expected: "low"
        System.out.println("get('love', 25): " + tm.get("love", 25)); // Expected: "low"
        
        System.out.println("get('hate', 10): " + tm.get("hate", 10)); // Expected: ""
        System.out.println("get('hate', 15): " + tm.get("hate", 15)); // Expected: "anger"
        System.out.println("get('hate', 20): " + tm.get("hate", 20)); // Expected: "anger"
        System.out.println("get('hate', 25): " + tm.get("hate", 25)); // Expected: "fury"
    }
    
    private void testEdgeCases() {
        TimeMapBinarySearch tm = new TimeMapBinarySearch();
        
        // Test non-existent key
        System.out.println("get('nonexistent', 1): " + tm.get("nonexistent", 1)); // Expected: ""
        
        // Test timestamp before any set timestamp
        tm.set("test", "value1", 10);
        System.out.println("get('test', 5): " + tm.get("test", 5)); // Expected: ""
        System.out.println("get('test', 10): " + tm.get("test", 10)); // Expected: "value1"
        System.out.println("get('test', 15): " + tm.get("test", 15)); // Expected: "value1"
        
        // Test single timestamp
        tm.set("single", "only", 100);
        System.out.println("get('single', 99): " + tm.get("single", 99));   // Expected: ""
        System.out.println("get('single', 100): " + tm.get("single", 100)); // Expected: "only"
        System.out.println("get('single', 101): " + tm.get("single", 101)); // Expected: "only"
    }
    
    private void testLargeTimestamps() {
        TimeMapBinarySearch tm = new TimeMapBinarySearch();
        
        // Test with large timestamps
        tm.set("large", "val1", 1000000);
        tm.set("large", "val2", 5000000);
        tm.set("large", "val3", 10000000);
        
        System.out.println("get('large', 500000): " + tm.get("large", 500000));     // Expected: ""
        System.out.println("get('large', 1000000): " + tm.get("large", 1000000));   // Expected: "val1"
        System.out.println("get('large', 3000000): " + tm.get("large", 3000000));   // Expected: "val1"
        System.out.println("get('large', 5000000): " + tm.get("large", 5000000));   // Expected: "val2"
        System.out.println("get('large', 7500000): " + tm.get("large", 7500000));   // Expected: "val2"
        System.out.println("get('large', 10000000): " + tm.get("large", 10000000)); // Expected: "val3"
        System.out.println("get('large', 15000000): " + tm.get("large", 15000000)); // Expected: "val3"
    }
} 