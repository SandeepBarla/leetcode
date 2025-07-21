package solutions.neetcode_150.hard;

import java.util.*;

/**
 * Leetcode 269: Alien Dictionary
 * 
 * Problem:
 * Given a list of words sorted lexicographically in an alien language, return the order of characters in that language.
 * If no valid ordering exists, return "".
 * 
 * Assumptions:
 * - All words consist of lowercase letters.
 * - The words are sorted according to the rules of the alien language.
 * - The character order must be a valid topological sort.
 * 
 * Approach:
 * 1. Build a graph using the given list of words.
 *    - For each pair of adjacent words, identify the first differing character.
 *    - This difference gives a directed edge from the earlier character to the later one.
 *    - Also validate for prefix issues like ["abc", "ab"] — which is invalid.
 * 
 * 2. Perform DFS for topological sort.
 *    - If a cycle is detected, return an empty string.
 *    - Otherwise, reverse the post-order result of the DFS.
 * 
 * Time Complexity: O(C + W)
 *   - C = number of unique characters
 *   - W = total length of all words (for graph building)
 * 
 * Space Complexity: O(C)
 *   - Graph, visited map, recursion stack, result list
 */
public class LC_269_AlienDictionary extends Solution {

    // Graph: Adjacency list representing ordering rules
    private Map<Character, Set<Character>> graph = new HashMap<>();

    // Visited map for DFS: true = currently visiting, false = visited
    private Map<Character, Boolean> visited = new HashMap<>();

    // Result list (post-order traversal)
    private List<Character> topoOrder = new ArrayList<>();

    public String foreignDictionary(String[] words) {

        // Step 1: Initialize graph with all unique characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        // Step 2: Build edges based on word ordering
        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];
            int minLen = Math.min(w1.length(), w2.length());

            // Invalid case: word1 is longer and starts with word2
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";

            // Add edge from first differing character
            for (int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    break;
                }
            }
        }

        // Step 3: DFS Topological Sort
        for (char node : graph.keySet()) {
            if (dfs(node)) return ""; // If cycle found, return ""
        }

        // Step 4: Build final result from reverse post-order
        StringBuilder result = new StringBuilder();
        for (char c : topoOrder) result.append(c);
        return result.reverse().toString();
    }

    private boolean dfs(char node) {
        if (visited.containsKey(node)) {
            return visited.get(node); // true if currently visiting (cycle)
        }

        visited.put(node, true); // Mark as visiting

        for (char neighbor : graph.get(node)) {
            if (dfs(neighbor)) return true; // Cycle detected
        }

        visited.put(node, false); // Mark as visited
        topoOrder.add(node);      // Post-order addition
        return false;
    }
}