package com.liyongquan.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/7/29
 */
public class PathInZigZagTree {
    /**
     * 满二叉树
     *
     * @param label
     * @return
     */
    public List<Integer> pathInZigZagTree(int label) {
        double d = (Math.log(label + 1) / Math.log(2));
        int depth = (int) d;
        if (d > (int) d) {
            depth += 1;
        }
        int size = (int) Math.pow(2, depth) - 1;
        int[] arr = new int[size];
        int num = 1;
        int labelIdx = 0;
        //下一层的开始
        int start = 0;
        for (int h = 0; h < depth; h++) {
            int len = (int) Math.pow(2, h);
            if (h % 2 == 0) {
                for (int i = start; i < start + len; i++) {
                    if (num == label) {
                        labelIdx = i;
                    }
                    arr[i] = num;
                    num++;
                }
            } else {
                for (int i = start + len - 1; i >= start; i--) {
                    if (num == label) {
                        labelIdx = i;
                    }
                    arr[i] = num;
                    num++;
                }
            }
            start += len;
        }
        //找到父节点
        LinkedList<Integer> res = new LinkedList<>();
        int idx = labelIdx;
        while (true) {
            res.offerFirst(arr[idx]);
            if (idx == 0) {
                break;
            }
            //获得父节点
            idx = (idx - 1) / 2;
        }
        return res;
    }
}
