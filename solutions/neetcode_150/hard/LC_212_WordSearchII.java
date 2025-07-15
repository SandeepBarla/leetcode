/*
 * LeetCode Problem 212: Word Search II
 * URL: https://leetcode.com/problems/word-search-ii/
 * Difficulty: Hard
 *
 * Approach:
 * - Use a Trie to store all words for efficient prefix checking.
 * - Perform DFS from every cell in the board.
 * - Use '#' to mark visited cells during DFS and backtrack after.
 * - If a word is found at a Trie node, add it to result and mark as null to avoid duplicates.
 *
 * Time Complexity: O(N*K + M*N*4^L), where:
 *   - N = number of words, K = avg length of words
 *   - M*N = board size, L = max length of a word
 * Space Complexity: O(N*K) for Trie + recursion stack
 */

package solutions.neetcode_150.hard;

import common.Solution;

import java.util.*;

public class LC_212_WordSearchII implements Solution {

  class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word = null; // stores the word ending here, null if none
  }

  public List<String> findWords(char[][] board, String[] words) {
    TrieNode root = buildTrie(words);
    List<String> result = new ArrayList<>();

    int rows = board.length;
    int cols = board[0].length;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        dfs(board, r, c, root, result);
      }
    }

    return result;
  }

  private TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();

    for (String word : words) {
      TrieNode curr = root;
      for (char ch : word.toCharArray()) {
        int idx = ch - 'a';
        if (curr.children[idx] == null) {
          curr.children[idx] = new TrieNode();
        }
        curr = curr.children[idx];
      }
      curr.word = word; // mark end of word
    }

    return root;
  }

  private void dfs(char[][] board, int row, int col, TrieNode node, List<String> result) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
      return;

    char currChar = board[row][col];
    if (currChar == '#' || node.children[currChar - 'a'] == null)
      return;

    node = node.children[currChar - 'a'];
    if (node.word != null) {
      result.add(node.word); // word found
      node.word = null; // prevent duplicates
    }

    board[row][col] = '#'; // mark visited

    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    for (int[] dir : directions) {
      dfs(board, row + dir[0], col + dir[1], node, result);
    }

    board[row][col] = currChar; // backtrack
  }

  @Override
  public void run() {
    char[][] board = {
        { 'o', 'a', 'a', 'n' },
        { 'e', 't', 'a', 'e' },
        { 'i', 'h', 'k', 'r' },
        { 'i', 'f', 'l', 'v' }
    };
    String[] words = { "oath", "pea", "eat", "rain" };

    List<String> found = findWords(board, words);
    System.out.println("Words found: " + found); // Expected: ["oath", "eat"]
  }
}