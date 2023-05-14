package com.liyongquan.heap;

// 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
//
//请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
//
// 
//
//示例 1：
//
//输入：barcodes = [1,1,1,2,2,2]
//输出：[2,1,2,1,2,1]
//示例 2：
//
//输入：barcodes = [1,1,1,1,2,2,3,3]
//输出：[1,3,1,3,2,1,2,1]
// 
//
//提示：
//
//1 <= barcodes.length <= 10000
//1 <= barcodes[i] <= 10000
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/distant-barcodes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;
import java.util.PriorityQueue;

public class RearrangeBarcodes {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Arrays.sort(barcodes);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        int l = 0, r = 0;
        while (r < barcodes.length) {
            if (barcodes[l] == barcodes[r]) {
                r++;
            } else {
                pq.add(new int[]{barcodes[l], r - l});
                l = r;
            }
        }
        pq.add(new int[]{barcodes[l], r - l});
        int[] res = new int[barcodes.length];
        int idx = 0;
        while (pq.size() > 0) {
            int[] p1 = pq.poll();
            if (pq.size() > 0) {
                int[] p2 = pq.poll();
                res[idx++] = p1[0];
                res[idx++] = p2[0];
                p1[1] -= 1;
                p2[1] -= 1;
                if (p1[1] > 0) {
                    pq.add(p1);
                }
                if (p2[1] > 0) {
                    pq.add(p2);
                }
            } else {
                res[idx++] = p1[0];
            }
        }
        return res;
    }
}
