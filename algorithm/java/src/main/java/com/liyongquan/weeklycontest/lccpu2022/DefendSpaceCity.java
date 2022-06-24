package com.liyongquan.weeklycontest.lccpu2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DefendSpaceCity {
    public int defendSpaceCity(int[] time, int[] position) {
        int len = time.length;
        List<int[]> t = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            t.add(new int[]{i, time[i]});
        }
        t.sort(Comparator.comparingInt(o -> o[1]));
        int[] pre = new int[101];
        int[] preUnion = new int[101];
        Arrays.fill(preUnion, -1);
        int[] cur = new int[101];
        int[] curUnion = new int[101];
        Arrays.fill(curUnion, -1);
        int l = 0;
        int r = 0;
        int res = 0;
        while (r < len) {
            int ct = t.get(l)[1];
            while (r < len && ct == t.get(r)[1]) {
                int[] c = t.get(r);
                int pos = position[c[0]];
                cur[pos] = 1;
                r++;
            }
            //连续开启的数量
            int cnt = 0;
            for (int i = 0; i < 101; i++) {
                if (cur[i] == 1) {
                    //已经开启联合护罩
                    if (curUnion[i] >= 0) {
                        //do nothing
                    } else if (pre[i] == 1) {
                        res++;
                        //联合护罩
                        if (preUnion[i] >= 0) {
                            curUnion[i] = preUnion[i];
                            curUnion[curUnion[i]] = i;
                        }
                        cnt = 0;
                    } else {
                        cnt++;
                        if (cnt == 2) {
                            res++;
                            cnt = 0;
                            curUnion[i] = i - 1;
                            curUnion[i - 1] = i;
                        } else {
                            res += 2;
                        }
                    }
                } else {
                    cnt = 0;
                }
            }
            l = r;
            for (int i = 0; i < 101; i++) {
                pre[i] = cur[i];
                cur[i] = 0;
                preUnion[i] = curUnion[i];
                curUnion[i] = -1;
            }
        }
        return res;
    }
}
