package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 5
 * <p>
 * 输出: 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-kth-magic-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class KthMagicNumber {
    /**
     * 错误，下层的节点不一定比这一层要大
     *
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int[] nums = {3, 5, 7};
        while (!queue.isEmpty()) {
            int size = queue.size();
            //存下一层的节点(去重)
            TreeSet<Integer> treeSet = new TreeSet<>();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                log.info("{}", poll);
                k--;
                if (k == 0) {
                    return poll;
                }
                for (int num : nums) {
                    treeSet.add(poll * num);
                }
            }
            log.info("----------");
            //按序放入队列
            for (Integer n : treeSet) {
                queue.add(n);
            }
        }
        return -1;
    }
}
