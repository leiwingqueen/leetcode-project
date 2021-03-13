package com.liyongquan.array;

import com.liyongquan.sort.HeapSort;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1215. 步进数
 * <p>
 * 如果一个整数上的每一位数字与其相邻位上的数字的绝对差都是 1，那么这个数就是一个「步进数」。
 * <p>
 * 例如，321 是一个步进数，而 421 不是。
 * <p>
 * 给你两个整数，low 和 high，请你找出在 [low, high] 范围内的所有步进数，并返回 排序后 的结果。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：low = 0, high = 21
 * 输出：[0,1,2,3,4,5,6,7,8,9,10,12,21]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= low <= high <= 2 * 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stepping-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SteppingNumbers {
    /**
     * 暴力解法
     * <p>
     * 超时
     *
     * @param low
     * @param high
     * @return
     */
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> res = new LinkedList<>();
        for (int i = low; i <= high; i++) {
            if (isStepNumbers(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isStepNumbers(int n) {
        if (n >= 0 && n < 9) {
            return true;
        }
        int pre = n % 10;
        n /= 10;
        while (n > 0) {
            if (Math.abs(n % 10 - pre) != 1) {
                return false;
            }
            pre = n % 10;
            n /= 10;
        }
        return true;
    }

    public List<Integer> countSteppingNumbers2(int low, int high) {
        List<Integer> res = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        //0单独处理
        if (low <= 0) {
            res.add(0);
        }
        for (int i = 1; i <= 9; i++) {
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            Integer num = queue.poll();
            if (num > high) {
                return res;
            }
            if (num >= low) {
                res.add(num);
            }
            //右边增加一个数字
            int right = num % 10;
            if (right > 0) {
                queue.add(num * 10 + right - 1);
            }
            if (right < 9) {
                queue.add(num * 10 + right + 1);
            }
        }
        return res;
    }

    private void dfs(int num, int low, int high, List<Integer> res) {
        int i = num % 10;
        if (i > 0) {
            int next = num * 10 + i - 1;
            if (next > high) {
                return;
            }
            if (next >= low && next <= high) {
                res.add(next);
                dfs(next, low, high, res);
            }
        }
        if (i < 9) {
            int next = num * 10 + i + 1;
            if (next > high) {
                return;
            }
            if (next >= low && next <= high) {
                res.add(next);
                dfs(next, low, high, res);
            }
        }
    }
}
