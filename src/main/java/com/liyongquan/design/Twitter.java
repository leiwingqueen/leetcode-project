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
    public static final int MAX_SIZE = 10;

    static class ListNode<T> {
        public T val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(T x) {
            val = x;
        }
    }

    static class User {
        //uid
        int userId;
        //关注人
        Set<Integer> followee;
        //自己实现链表的话才能后续比较灵活地做归并排序
        ListNode<Message> head;

        public User(int userId) {
            this.userId = userId;
            this.followee = new HashSet<>();
        }

        public void postTweet(int tweetId, int time) {
            ListNode node = new ListNode(new Message(tweetId, time));
            if (head != null) {
                node.next = head;
            }
            head = node;
        }

        public void follow(int followeeId) {
            if (followeeId == userId) {
                return;
            }
            followee.add(followeeId);
        }

        public void unfollow(int followeeId) {
            if (followeeId == userId) {
                return;
            }
            followee.remove(followeeId);
        }
    }

    /**
     * 推特信息
     */
    static class Message {
        int tweetId;
        //时间，先采用自增解决
        int time;

        public Message(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    //存储用户信息
    private Map<Integer, User> userMap;

    private int time = 0;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        userMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User user = userMap.get(userId);
        user.postTweet(tweetId, ++time);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId)) {
            return Collections.emptyList();
        }
        User user = userMap.get(userId);
        //合并K个链表的算法，堆排+归并，构造一个大顶堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>(user.followee.size() + 1, (o1, o2) -> ((Message) o2.val).time - ((Message) o1.val).time);
        //需要倒序输出，这里选择用desc
        if (user.head != null) {
            queue.add(user.head);
        }
        for (Integer followeeId : user.followee) {
            if (userMap.containsKey(followeeId) && userMap.get(followeeId).head != null) {
                queue.add(userMap.get(followeeId).head);
            }
        }
        List<Integer> res = new LinkedList<>();
        while (res.size() < MAX_SIZE && queue.size() > 0) {
            ListNode poll = queue.poll();
            res.add(((Message) poll.val).tweetId);
            if (poll.next != null) {
                queue.add(poll.next);
            }
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        userMap.get(followerId).follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        userMap.get(followerId).unfollow(followeeId);
    }
}
