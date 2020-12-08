package com.liyongquan.backtrack;

import java.util.*;

/**
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * <p>
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 * <p>
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * <p>
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："123456579"
 * 输出：[123,456,579]
 * 示例 2：
 * <p>
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 示例 3：
 * <p>
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 示例 4：
 * <p>
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 示例 5：
 * <p>
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SplitIntoFibonacci {
    public List<Integer> splitIntoFibonacci(String S) {
        return backtrack(new LinkedList<>(), S, 0);
    }

    public List<Integer> backtrack(Deque<Integer> path, String s, int idx) {
        //System.out.println("==================== path size:" + path.size() + ",idx:" + idx);
        if (idx >= s.length()) {
            return new ArrayList<>(path);
        }
        //直接可以确定下一个数字
        if (path.size() >= 2) {
            Integer p1 = path.pollLast();
            Integer p2 = path.pollLast();
            int next = p1 + p2;
            //继续放回队列中
            path.add(p2);
            path.add(p1);
            int[] nums = transfer(next);
            //扫描是否匹配
            if (s.length() - idx < nums.length) {
                return Collections.emptyList();
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != s.charAt(idx + i) - '0') {
                    return Collections.emptyList();
                }
            }
            path.add(next);
            System.out.println("加入数字:" + next + ",next idx:" + (idx + nums.length));
            List<Integer> backtrack = backtrack(path, s, idx + nums.length);
            if (backtrack.size() > 0) {
                return backtrack;
            }
            //还原现场
            path.pollLast();
        } else {
            //特殊处理下一个数字为0，只能选0
            if (s.charAt(idx) == '0') {
                path.add(0);
                //System.out.println("加入数字:" + 0);
                return backtrack(path, s, idx + 1);
            }
            //适当剪枝，第三个元素的总长度需要比第一个元素和第二个元素的最大长度要长
            Integer p1 = path.size() == 0 ? 0 : path.peekLast();
            int[] nums = transfer(p1);
            int num = 0;
            for (int i = idx; i < s.length(); i++) {
                //第3个数字的最小长度
                int len = Math.max(i - idx + 1, nums.length);
                System.out.println("len:" + len + ",i:" + i + ",idx:" + idx);
                //第三个数字的长度不满足最小长度，直接退出
                if (s.length() - i - 1 < len) {
                    break;
                }
                num = num * 10 + (s.charAt(i) - '0');
                path.add(num);
                System.out.println("加入数字:" + num + ",next idx:" + (i + 1) + ",path size:" + path.size());
                List<Integer> backtrack = backtrack(path, s, i + 1);
                if (backtrack.size() > 0) {
                    return backtrack;
                }
                //回溯
                Integer poll = path.pollLast();
                System.out.println("弹出数字:" + poll + ",path size:" + path.size());
            }
        }
        return Collections.emptyList();
    }

    /**
     * 数字转化成数组
     *
     * @param num
     * @return
     */
    private int[] transfer(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        //栈
        Deque<Integer> deque = new LinkedList<>();
        while (num != 0) {
            int mod = num % 10;
            deque.offerFirst(mod);
            num /= 10;
        }
        int[] res = new int[deque.size()];
        int idx = 0;
        while (deque.size() > 0) {
            Integer n = deque.pollFirst();
            res[idx++] = n;
        }
        return res;
    }
}
