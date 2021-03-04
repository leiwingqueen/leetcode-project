package com.liyongquan.dp;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题
 * <p>
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxEnvelopes {
    /**
     * 贪心算法？
     * 先排序，再计算
     * <p>
     * 不通过
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len <= 1) {
            return len;
        }
        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int res = 1, pre = 0;
        for (int i = 1; i < len; i++) {
            if (envelopes[i][0] > envelopes[pre][0] && envelopes[i][1] > envelopes[pre][1]) {
                res++;
                pre = i;
            }
        }
        return res;
    }

    /**
     * 最终简化成选和不选的问题
     * 先尝试用回溯方法解
     *
     * 超时
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes2(int[][] envelopes) {
        int len = envelopes.length;
        if (len <= 1) {
            return len;
        }
        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        return backtrace(envelopes, 0, new int[]{0, 0});
    }

    private int backtrace(int[][] envelopes, int idx, int[] max) {
        if (idx == envelopes.length) {
            return 0;
        }
        int res = 0;
        //选择
        if (envelopes[idx][0] > max[0] && envelopes[idx][1] > max[1]) {
            res = backtrace(envelopes, idx + 1, envelopes[idx]) + 1;
        }
        //不选
        res = Math.max(res, backtrace(envelopes, idx + 1, max));
        return res;
    }
}
