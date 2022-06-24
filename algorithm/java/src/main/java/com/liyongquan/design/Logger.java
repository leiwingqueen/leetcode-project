package com.liyongquan.design;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请你设计一个日志系统，可以流式接收消息以及它的时间戳。每条 不重复 的消息最多只能每 10 秒打印一次。也就是说，如果在时间戳 t 打印某条消息，那么相同内容的消息直到时间戳变为 t + 10 之前都不会被打印。
 * <p>
 * 所有消息都按时间顺序发送。多条消息可能到达同一时间戳。
 * <p>
 * 实现 Logger 类：
 * <p>
 * Logger() 初始化 logger 对象
 * bool shouldPrintMessage(int timestamp, string message) 如果这条消息 message 在给定的时间戳 timestamp 应该被打印出来，则返回 true ，否则请返回 false 。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
 * 输出：
 * [null, true, true, false, false, false, true]
 * <p>
 * 解释：
 * Logger logger = new Logger();
 * logger.shouldPrintMessage(1, "foo");  // 返回 true ，下一次 "foo" 可以打印的时间戳是 1 + 10 = 11
 * logger.shouldPrintMessage(2, "bar");  // 返回 true ，下一次 "bar" 可以打印的时间戳是 2 + 10 = 12
 * logger.shouldPrintMessage(3, "foo");  // 3 < 11 ，返回 false
 * logger.shouldPrintMessage(8, "bar");  // 8 < 12 ，返回 false
 * logger.shouldPrintMessage(10, "foo"); // 10 < 11 ，返回 false
 * logger.shouldPrintMessage(11, "foo"); // 11 >= 11 ，返回 true ，下一次 "foo" 可以打印的时间戳是 11 + 10 = 21
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= timestamp <= 109
 * 每个 timestamp 都将按非递减顺序（时间顺序）传递
 * 1 <= message.length <= 30
 * 最多调用 104 次 shouldPrintMessage 方法
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/logger-rate-limiter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Logger {
    /**
     * 对每个消息都维护一个下一次能够发送的时间，如果时间小于这个时间，则不允许发送
     */
    private Map<String, Integer> map;

    /**
     * Initialize your data structure here.
     */
    public Logger() {
        map = new ConcurrentHashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        synchronized (this) {
            Integer time = map.getOrDefault(message, 0);
            if (timestamp >= time) {
                map.put(message, timestamp + 10);
                return true;
            } else {
                return false;
            }
        }
    }
}
