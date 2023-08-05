package com.liyongquan.hash;

import java.util.*;

public class GroupAlg {
    public static final int GROUP_SIZE = 4;

    public List<List<String>> group(String[] peoples) {
        int unnamed = 0;
        Map<String, Integer> counter = new HashMap<>();
        for (String people : peoples) {
            if ("".equals(people)) {
                unnamed++;
            } else {
                counter.put(people, counter.getOrDefault(people, 0) + 1);
            }
        }
        List<List<String>> res = new ArrayList<>();
        Queue<List<String>> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            while (queue.size() < count) {
                queue.offer(new ArrayList<>());
            }
            Iterator<List<String>> iter = queue.iterator();
            for (int i = 0; i < count; i++) {
                List<String> list = iter.next();
                list.add(name);
            }
            // 扫描已经满的组加入到结果集，实际上这里可以前置处理，不过这里为了简单起见，就放在这里
            while (queue.size() > 0 && queue.peek().size() == GROUP_SIZE) {
                res.add(queue.poll());
            }
        }
        // 处理没有名字的玩家，随意塞到一组即可
        while (unnamed > 0) {
            if (queue.size() > 0) {
                List<String> list = queue.poll();
                int c = Math.min(GROUP_SIZE - list.size(), unnamed);
                unnamed -= c;
                for (int i = 0; i < c; i++) {
                    list.add("");
                }
                res.add(list);
            } else {
                int c = Math.min(GROUP_SIZE, unnamed);
                unnamed -= c;
                List<String> list = new ArrayList<>();
                for (int i = 0; i < c; i++) {
                    list.add("");
                }
                res.add(list);
            }
        }
        while (queue.size() > 0) {
            res.add(queue.poll());
        }
        return res;
    }
}
