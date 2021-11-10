package com.liyongquan.design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 滑动窗口统计QPS
 *
 * @author liyongquan
 * @date 2021/11/10
 */
public class SlideWindow {
    //10个slot组成一个bucket进行统计
    public static final int SLOT_NUM = 10;
    //100ms一个slot
    public static final int SLOT_INTERVAL = 100;
    private Deque<Slot> slots;

    public SlideWindow() {
        slots = new LinkedList<>();
    }

    //得到当前的QPS
    public int getQPS() {
        long now = System.currentTimeMillis();
        expire(now);
        int sum = 0;
        for (Slot slot : slots) {
            sum += slot.num;
        }
        return sum;
    }


    public int inc(int num) {
        long now = System.currentTimeMillis();
        expire(now);
        //slot的时间戳
        long t = now - now % SLOT_INTERVAL;
        //更新最后一个slot
        if (!slots.isEmpty() && slots.peekLast().timestamp == t) {
            Slot last = slots.peekLast();
            last.num += num;
        } else {
            Slot slot = new Slot(num, t);
            slots.offerLast(slot);
        }
        int sum = 0;
        for (Slot slot : slots) {
            sum += slot.num;
        }
        return sum;
    }

    private void expire(long now) {
        //pop过时的slot
        while (!slots.isEmpty() && slots.peekFirst().timestamp < now - SLOT_INTERVAL * SLOT_NUM) {
            slots.pollFirst();
        }
    }

    static class Slot {
        int num;
        long timestamp;

        public Slot(int num, long timestamp) {
            this.num = num;
            this.timestamp = timestamp;
        }
    }
}
