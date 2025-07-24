/*
 * LeetCode Problem 355: Design Twitter
 * URL: https://leetcode.com/problems/design-twitter/
 * Difficulty: Medium
 *
 * Approach:
 * - Use a HashMap<Integer, Set<Integer>> to track followees per user.
 * - Use a HashMap<Integer, List<int[]>> to track tweets per user, where each tweet is [timestamp, tweetId].
 * - Use a global counter to simulate timestamps.
 * - For getNewsFeed, gather tweets from the user and their followees, and maintain a min-heap of the 10 most recent.
 * - Use HashSet instead of List to avoid duplicate follow relationships and speed up lookup/removal.
 *
 * Time Complexity:
 * - postTweet: O(1)
 * - follow / unfollow: O(1)
 * - getNewsFeed: O(k * log k) where k = total tweets considered (capped by top 10 per user)
 *
 * Space Complexity: O(n + t), where n is number of users and t is number of tweets
 */

package solutions.neetcode_150.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import common.Solution;

public class LC_355_DesignTwitter implements Solution {

  static class Twitter {
    private Map<Integer, Set<Integer>> followees;
    private Map<Integer, List<int[]>> tweets;
    private int timestamp;

    public Twitter() {
      followees = new HashMap<>();
      tweets = new HashMap<>();
      timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {
      tweets.putIfAbsent(userId, new ArrayList<>());
      tweets.get(userId).add(new int[] { timestamp++, tweetId });
    }

    public List<Integer> getNewsFeed(int userId) {
      PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
      Set<Integer> allUsers = new HashSet<>(followees.getOrDefault(userId, new HashSet<>()));
      allUsers.add(userId); // include self

      for (int uid : allUsers) {
        List<int[]> userTweets = tweets.getOrDefault(uid, new ArrayList<>());
        for (int i = userTweets.size() - 1; i >= 0 && userTweets.size() - i <= 10; i--) {
          minHeap.offer(userTweets.get(i));
          if (minHeap.size() > 10) {
            minHeap.poll();
          }
        }
      }

      List<Integer> result = new ArrayList<>();
      while (!minHeap.isEmpty()) {
        result.add(minHeap.poll()[1]);
      }
      Collections.reverse(result);
      return result;
    }

    public void follow(int followerId, int followeeId) {
      if (followerId == followeeId)
        return;
      followees.putIfAbsent(followerId, new HashSet<>());
      followees.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
      followees.getOrDefault(followerId, new HashSet<>()).remove(followeeId);
    }
  }

  @Override
  public void run() {
    Twitter twitter = new Twitter();

    twitter.postTweet(1, 5);
    twitter.follow(1, 2);
    twitter.postTweet(2, 6);

    System.out.println("Feed for user 1: " + twitter.getNewsFeed(1)); // Expected: [6, 5]

    twitter.unfollow(1, 2);
    System.out.println("Feed for user 1 after unfollow: " + twitter.getNewsFeed(1)); // Expected: [5]
  }
}