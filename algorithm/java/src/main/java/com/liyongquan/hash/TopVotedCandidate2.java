package com.liyongquan.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 前面的基础上做预计算
 *
 * @author liyongquan
 * @date 2021/12/11
 */
public class TopVotedCandidate2 {
    private int[] wins;
    private int[] times;


    public TopVotedCandidate2(int[] persons, int[] times) {
        this.wins = new int[times.length];
        this.times = times;
        //分别计算每个时间点胜出的人
        int max = 0;
        int p = -1;
        Map<Integer, Integer> votes = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int person = persons[i];
            votes.put(person, votes.getOrDefault(person, 0) + 1);
            if (votes.get(person) >= max) {
                p = person;
                max = votes.get(person);
            }
            wins[i] = p;
        }
    }

    //这里做二分查找找到对应的下标即可
    public int q(int t) {
        int l = 0, r = times.length - 1;
        while (l < r) {
            //这里是关键，取右边界
            int mid = l + (r - l + 1) / 2;
            if (times[mid] <= t) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return wins[l];
    }
}
