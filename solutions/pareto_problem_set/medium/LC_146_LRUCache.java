/*
 * LeetCode Problem 146: LRU Cache
 * URL: https://leetcode.com/problems/lru-cache/
 * Difficulty: Medium
 *
 * Approach: HashMap + Doubly Linked List
 * - HashMap stores key → Node
 * - Doubly linked list maintains usage order (most recent at tail)
 * - On get/put: move node to tail (most recently used)
 * - On put (new key): if over capacity, remove node at head (LRU)
 *
 * Time Complexity: O(1) for get and put
 * Space Complexity: O(capacity)
 */
package solutions.pareto_problem_set.medium;

import java.util.HashMap;
import java.util.Map;

import common.Solution;

public class LC_146_LRUCache implements Solution {

  private final int capacity;
  private final Map<Integer, Node> map;
  private final Node head;
  private final Node tail;

  public LC_146_LRUCache(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>();
    this.head = new Node(0, 0); // dummy head
    this.tail = new Node(0, 0); // dummy tail
    head.next = tail;
    tail.prev = head;
  }

  // No-arg constructor for Main.java compatibility
  public LC_146_LRUCache() {
    this(2); // default capacity for testing
  }

  public int get(int key) {
    if (!map.containsKey(key))
      return -1;

    Node node = map.get(key);
    remove(node);
    insertAtTail(node);

    return node.value;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node existing = map.get(key);
      existing.value = value;
      remove(existing);
      insertAtTail(existing);
    } else {
      if (map.size() >= capacity) {
        Node lru = head.next; // first real node
        remove(lru);
        map.remove(lru.key);
      }
      Node node = new Node(key, value);
      insertAtTail(node);
      map.put(key, node);
    }
  }

  // Remove node from DLL
  private void remove(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  // Insert node at tail (most recently used)
  private void insertAtTail(Node node) {
    node.prev = tail.prev;
    node.next = tail;

    tail.prev.next = node;
    tail.prev = node;
  }

  private static class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int k, int v) {
      this.key = k;
      this.value = v;
    }
  }

  @Override
  public void run() {
    LC_146_LRUCache cache = new LC_146_LRUCache(2);

    cache.put(1, 1); // cache: 1
    cache.put(2, 2); // cache: 1,2
    System.out.println("get(1): " + cache.get(1)); // returns 1

    cache.put(3, 3); // evicts 2 → cache: 1,3
    System.out.println("get(2): " + cache.get(2)); // returns -1

    cache.put(4, 4); // evicts 1 → cache: 3,4
    System.out.println("get(1): " + cache.get(1)); // returns -1
    System.out.println("get(3): " + cache.get(3)); // returns 3
    System.out.println("get(4): " + cache.get(4)); // returns 4
  }
}