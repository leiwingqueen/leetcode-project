package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * //格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * //
 * // 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * //
 * // 格雷编码序列必须以 0 开头。
 * //
 * //
 * //
 * // 示例 1:
 * //
 * // 输入: 2
 * //输出: [0,1,3,2]
 * //解释:
 * //00 - 0
 * //01 - 1
 * //11 - 3
 * //10 - 2
 * //
 * //对于给定的 n，其格雷编码序列并不唯一。
 * //例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * //
 * //00 - 0
 * //10 - 2
 * //11 - 3
 * //01 - 1
 * //
 * // 示例 2:
 * //
 * // 输入: 0
 * //输出: [0]
 * //解释: 我们定义格雷编码序列必须以 0 开头。
 * //     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 * //     因此，当 n = 0 时，其格雷编码序列为 [0]。
 * //
 * // Related Topics 回溯算法
 * // 👍 293 👎 0
 */
@Slf4j
public class GrayCode {
    private int[] exist;
    private int[] res;
    private int n;
    private int size;

    /**
     * 中规中矩的回溯解法
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        this.size = (int) Math.pow(2, n);
        this.exist = new int[size];
        this.res = new int[size];
        this.n = n;
        this.exist[0] = 1;
        backtrace(1, 0);
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(res[i]);
        }
        return list;
    }

    private boolean backtrace(int idx, int cur) {
        if (idx == size) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            int next = cur ^ (1 << i);
            if (exist[next] == 0) {
                res[idx] = next;
                exist[next] = 1;
                //log.info("idx:{},value:{}", idx, next);
                if (backtrace(idx + 1, next)) {
                    return true;
                }
                exist[next] = 0;
            }
        }
        return false;
    }
}
