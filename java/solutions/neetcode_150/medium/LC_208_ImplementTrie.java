/*
 * LeetCode Problem 208: Implement Trie (Prefix Tree)
 * URL: https://leetcode.com/problems/implement-trie-prefix-tree/
 * Difficulty: Medium
 *
 * Optimizations:
 * - Use TrieNode[] array instead of HashMap for children (only 'a' to 'z')
 * - Removed unnecessary 'char val' field from node
 * - isWord flag used to mark end of word
 *
 * Time Complexity:
 * - insert: O(n)
 * - search: O(n)
 * - startsWith: O(n)
 *
 * Space Complexity:
 * - O(n * Σ) where Σ is character set (26 here)
 */

package solutions.neetcode_150.medium;

import common.Solution;

public class LC_208_ImplementTrie implements Solution {

  class TrieNode {
    TrieNode[] children = new TrieNode[26]; // For 'a' to 'z'
    boolean isWord = false;
  }

  class Trie {
    private final TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode node = root;
      for (char c : word.toCharArray()) {
        int idx = c - 'a';
        if (node.children[idx] == null) {
          node.children[idx] = new TrieNode();
        }
        node = node.children[idx];
      }
      node.isWord = true;
    }

    public boolean search(String word) {
      TrieNode node = root;
      for (char c : word.toCharArray()) {
        int idx = c - 'a';
        if (node.children[idx] == null) {
          return false;
        }
        node = node.children[idx];
      }
      return node.isWord;
    }

    public boolean startsWith(String prefix) {
      TrieNode node = root;
      for (char c : prefix.toCharArray()) {
        int idx = c - 'a';
        if (node.children[idx] == null) {
          return false;
        }
        node = node.children[idx];
      }
      return true;
    }
  }

  @Override
  public void run() {
    Trie trie = new Trie();

    trie.insert("apple");
    System.out.println("Search 'apple': " + trie.search("apple")); // true
    System.out.println("Search 'app': " + trie.search("app")); // false
    System.out.println("StartsWith 'app': " + trie.startsWith("app")); // true

    trie.insert("app");
    System.out.println("Search 'app': " + trie.search("app")); // true

    trie.insert("bat");
    System.out.println("Search 'bat': " + trie.search("bat")); // true
    System.out.println("StartsWith 'ba': " + trie.startsWith("ba")); // true
    System.out.println("Search 'bad': " + trie.search("bad")); // false
  }
}