package com.liyongquan.design;

import java.util.*;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * <p>
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * 示例:
 * <p>
 * Twitter twitter = new Twitter();
 * <p>
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 * <p>
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-twitter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Twitter {
    /**
     * 最简单的思路就是用hashmap保存一个用户的推特列表
     * <p>
     * 用户关注的关联关系也用hashmap来保存
     */

    //双端队列保留最近10条推特
    private Map<Integer, Deque<Integer>> tweet;

    //关注关系
    private Map<Integer, Set<Integer>> follow;

    private int maxSize;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        tweet = new HashMap<>();
        follow = new HashMap<>();
        maxSize = 10;
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        Deque<Integer> deque = tweet.containsKey(userId) ? tweet.get(userId) : new LinkedList<>();
        deque.add(tweetId);
        tweet.put(userId, deque);
        if (deque.size() > maxSize) {
            deque.pollFirst();
        }
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> set = follow.containsKey(userId) ? follow.get(userId) : new HashSet<>();
        //归并排序
        List<Iterator<Integer>> list = new LinkedList<>();
        for (Integer item : set) {
            if (tweet.containsKey(item)) {
                list.add(tweet.get(item).descendingIterator());
            }
        }
        //加上自己
        if (tweet.containsKey(userId)) {
            list.add(tweet.get(userId).descendingIterator());
        }
        List<Integer> res = new ArrayList<>(maxSize);
        while (res.size() < maxSize) {
            Iterator<Integer> it;
            int max = -1;
            for (Iterator<Integer> iterator : list) {
                if (!iterator.hasNext()) {
                    continue;
                }
                //TODO
            }
        }
        return null;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        Set<Integer> set = follow.containsKey(followeeId) ? follow.get(followerId) : new HashSet<>();
        set.add(followeeId);
        follow.put(followerId, set);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (!follow.containsKey(followerId)) {
            return;
        }
        Set<Integer> set = follow.get(followerId);
        set.remove(followeeId);
    }
}
