package com.liyongquan.bfs;

//433. 最小基因变化
//基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
//
//假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
//
//例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
//另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
//
//给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
//
//注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
//
// 
//
//示例 1：
//
//输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
//输出：1
//示例 2：
//
//输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
//输出：2
//示例 3：
//
//输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
//输出：3
// 
//
//提示：
//
//start.length == 8
//end.length == 8
//0 <= bank.length <= 10
//bank[i].length == 8
//start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

public class MinMutation {
    public int minMutation(String start, String end, String[] bank) {
        //构造无向图
        Map<String, Integer> nodes = new HashMap<>();
        List<String> list = new ArrayList<>(bank.length + 1);
        int idx = 0;
        for (String s : bank) {
            if (!nodes.containsKey(s)) {
                nodes.put(s, idx++);
                list.add(s);
            }
        }
        if (!nodes.containsKey(start)) {
            nodes.put(start, idx);
            list.add(start);
        }
        int startIdx = nodes.get(start);
        if (!nodes.containsKey(end)) {
            return -1;
        }
        int endIdx = nodes.get(end);
        if (startIdx == endIdx) {
            return 0;
        }
        int size = nodes.size();
        boolean[][] graph = new boolean[size][size];
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (checkDiff(list.get(i), list.get(j))) {
                    graph[i][j] = true;
                    graph[j][i] = true;
                }
            }
        }
        //bfs查找最短路径
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIdx);
        boolean[] visit = new boolean[size];
        visit[startIdx] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int poll = queue.poll();
                if (poll == endIdx) {
                    return depth;
                }
                for (int j = 0; j < size; j++) {
                    if (graph[poll][j] && !visit[j]) {
                        visit[j] = true;
                        queue.add(j);
                    }
                }
            }
            depth++;
        }
        return -1;
    }

    private boolean checkDiff(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < 8; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
