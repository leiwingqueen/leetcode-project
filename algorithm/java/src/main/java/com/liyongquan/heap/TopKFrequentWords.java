package com.liyongquan.heap;

import java.util.*;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        //统计
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //构建小顶堆
        PriorityQueue<String> pq = new PriorityQueue<>(k, (o1, o2) -> {
            int i = map.get(o1) - map.get(o2);
            if (i != 0) {
                return i;
            }
            return o2.compareTo(o1);
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(entry.getKey());
            } else if (entry.getValue() > map.get(pq.peek()) || (entry.getValue() == map.get(pq.peek()) && entry.getKey().compareTo(pq.peek()) < 0)) {
                pq.poll();
                pq.add(entry.getKey());
            }
        }
        //输出
        List<String> res = new LinkedList<>();
        while (pq.size() > 0) {
            ((Deque<String>) res).addFirst(pq.poll());
        }
        return res;
    }
}
