/*
 * LeetCode Problem 211: Design Add and Search Words Data Structure
 * URL: https://leetcode.com/problems/design-add-and-search-words-data-structure/
 * Difficulty: Medium
 *
 * Approach:
 * - Build a Trie where each node holds an array of 26 children and a boolean flag.
 * - For `addWord`, insert characters like a standard Trie.
 * - For `search`, use DFS:
 *   - If the current character is not '.', go to the corresponding child.
 *   - If it's '.', try all non-null children recursively.
 *
 * Time Complexity:
 * - addWord: O(L), where L = word length
 * - search: O(26^d), where d = number of dots (wildcards)
 *
 * Space Complexity: O(N * L), where N = number of words, L = average word length
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_211_DesignAddAndSearchWords implements Solution {

  static class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
      root = new TrieNode();
    }

    public void addWord(String word) {
      TrieNode node = root;
      for (char c : word.toCharArray()) {
        int index = c - 'a';
        if (node.children[index] == null) {
          node.children[index] = new TrieNode();
        }
        node = node.children[index];
      }
      node.isEnd = true;
    }

    public boolean search(String word) {
      return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, TrieNode node) {
      if (i == word.length())
        return node.isEnd;

      char c = word.charAt(i);
      if (c == '.') {
        for (TrieNode child : node.children) {
          if (child != null && dfs(word, i + 1, child))
            return true;
        }
        return false;
      }

      int index = c - 'a';
      TrieNode child = node.children[index];
      return child != null && dfs(word, i + 1, child);
    }

    private static class TrieNode {
      TrieNode[] children = new TrieNode[26];
      boolean isEnd = false;
    }
  }

  @Override
  public void run() {
    WordDictionary dict = new WordDictionary();
    dict.addWord("bad");
    dict.addWord("dad");
    dict.addWord("mad");

    System.out.println(dict.search("pad")); // false
    System.out.println(dict.search("bad")); // true
    System.out.println(dict.search(".ad")); // true
    System.out.println(dict.search("b..")); // true
  }
}