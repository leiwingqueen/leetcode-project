package com.liyongquan.design;

import javafx.util.Pair;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 359. 日志速率限制器
 */
public class Logger2 {
    /**
     * 使用滑动窗口定期清除过期的数据，只需要保留最近10s的数据
     */
    private Deque<Pair<Integer, String>> queue = new LinkedList<>();
    private Set<String> set = new HashSet<>();

    public Logger2() {
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        //清理过期数据
        while (queue.size() > 0 && timestamp - queue.peekFirst().getKey() > 10) {
            Pair<Integer, String> head = queue.pollFirst();
            set.remove(head.getValue());
        }
        if (set.contains(message)) {
            return false;
        } else {
            queue.offerLast(new Pair<>(timestamp, message));
            set.add(message);
            return true;
        }
    }
}
