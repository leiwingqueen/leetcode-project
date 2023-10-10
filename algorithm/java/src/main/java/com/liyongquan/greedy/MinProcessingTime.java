package com.liyongquan.greedy;

import com.liyongquan.array.Sort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MinProcessingTime {
    // 错误，每个核只能处理一个任务
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Integer time : processorTime) {
            res = Math.max(time, res);
            for (int i = 0; i < 4; i++) {
                pq.add(time);
            }
        }
        tasks.sort((o1, o2) -> o2 - o1);
        for (Integer task : tasks) {
            Integer time = pq.poll();
            int next = time + task;
            res = Math.max(next, res);
            pq.add(next);
        }
        return res;
    }

    // 贪心策略，每次选择最小的处理时间的核来处理最大的任务
    public int minProcessingTime2(List<Integer> processorTime, List<Integer> tasks) {
        int res = 0;
        processorTime.sort(Comparator.comparingInt(o -> o));
        tasks.sort((o1, o2) -> o2 - o1);
        Iterator<Integer> taskIter = tasks.iterator();
        for (Integer time : processorTime) {
            for (int i = 0; i < 4; i++) {
                Integer task = taskIter.next();
                res = Math.max(task + time, res);
            }
        }
        return res;
    }
}
