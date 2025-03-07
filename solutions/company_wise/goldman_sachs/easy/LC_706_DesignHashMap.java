package solutions.company_wise.goldman_sachs.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import common.Solution;

public class LC_706_DesignHashMap implements Solution {

  // ✅ Optimized MyHashMap (Using LinkedList for Separate Chaining)
  static class MyHashMap {
    private static final int SIZE = 1000; // Number of buckets (fixed)
    private LinkedList<Entry>[] buckets;

    // Constructor: Initialize bucket array
    public MyHashMap() {
      buckets = new LinkedList[SIZE];
    }

    // Hash function to map keys to bucket index
    private int hash(int key) {
      return key % SIZE;
    }

    // Insert or update (key, value) in HashMap
    public void put(int key, int value) {
      int index = hash(key);
      if (buckets[index] == null) {
        buckets[index] = new LinkedList<>();
      }

      // Check if key already exists, update value
      for (Entry entry : buckets[index]) {
        if (entry.key == key) {
          entry.value = value;
          return;
        }
      }

      // If key is not found, add new key-value pair
      buckets[index].add(new Entry(key, value));
    }

    // Get the value associated with key
    public int get(int key) {
      int index = hash(key);
      if (buckets[index] == null)
        return -1;

      for (Entry entry : buckets[index]) {
        if (entry.key == key)
          return entry.value;
      }

      return -1;
    }

    // Remove key from HashMap
    public void remove(int key) {
      int index = hash(key);
      if (buckets[index] == null)
        return;

      buckets[index].removeIf(entry -> entry.key == key);
    }

    // Node structure for (key, value) pair
    private static class Entry {
      int key, value;

      Entry(int key, int value) {
        this.key = key;
        this.value = value;
      }
    }
  }

  // 🚀 Alternative MyHashMap using `ArrayList` for Buckets (Not recommended)
  static class MyHashMapArrayList {
    private static final int SIZE = 1000;
    private List<Entry>[] buckets;

    public MyHashMapArrayList() {
      buckets = new ArrayList[SIZE];
      for (int i = 0; i < SIZE; i++) {
        buckets[i] = new ArrayList<>();
      }
    }

    private int hash(int key) {
      return key % SIZE;
    }

    public void put(int key, int value) {
      int index = hash(key);
      for (Entry entry : buckets[index]) {
        if (entry.key == key) {
          entry.value = value;
          return;
        }
      }
      buckets[index].add(new Entry(key, value));
    }

    public int get(int key) {
      int index = hash(key);
      for (Entry entry : buckets[index]) {
        if (entry.key == key)
          return entry.value;
      }
      return -1;
    }

    public void remove(int key) {
      int index = hash(key);
      buckets[index].removeIf(entry -> entry.key == key);
    }

    private static class Entry {
      int key, value;

      Entry(int key, int value) {
        this.key = key;
        this.value = value;
      }
    }
  }

  @Override
  public void run() {
    // 🔹 Test Case 1: Using LinkedList HashMap
    MyHashMap hashMap = new MyHashMap();
    System.out.println("Using LinkedList HashMap:");
    hashMap.put(1, 10);
    hashMap.put(2, 20);
    hashMap.put(3, 30);
    System.out.println("Get key 2: " + hashMap.get(2)); // 20
    hashMap.remove(2);
    System.out.println("Get key 2 after removal: " + hashMap.get(2)); // -1

    // 🔹 Test Case 2: Using ArrayList HashMap
    MyHashMapArrayList hashMapArrayList = new MyHashMapArrayList();
    System.out.println("\nUsing ArrayList HashMap:");
    hashMapArrayList.put(1, 10);
    hashMapArrayList.put(2, 20);
    hashMapArrayList.put(3, 30);
    System.out.println("Get key 2: " + hashMapArrayList.get(2)); // 20
    hashMapArrayList.remove(2);
    System.out.println("Get key 2 after removal: " + hashMapArrayList.get(2)); // -1
  }
}