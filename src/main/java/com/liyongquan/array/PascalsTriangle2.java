package com.liyongquan.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PascalsTriangle2 {
    /**
     * 跟上一题类似，只是我们可以只保留上一行的数据结果即可
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex <= 0) {
            return Arrays.asList(1);
        }
        List<Integer> pre = new ArrayList<>(rowIndex + 1), r = new ArrayList<>(rowIndex + 1);
        //这里全部填1，就减少了后面set的越界判断
        for (int i = 0; i <= rowIndex; i++) {
            pre.add(1);
            r.add(1);
        }
        for (int i = 1; i <= rowIndex; i++) {
            //这里要用覆盖操作
            r.set(0, 1);
            if (i > 0) {
                //填充中间的元素
                for (int j = 1; j < i; j++) {
                    r.set(j, pre.get(j - 1) + pre.get(j));
                }
                r.set(i, 1);
            }
            //交换两个list
            List<Integer> tmp = pre;
            pre = r;
            r = tmp;
        }
        return pre;
    }

    /**
     * 先不做任何优化
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow2(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>(rowIndex + 1);
        res.add(Arrays.asList(1));
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> list = new ArrayList<>(rowIndex + 1);
            list.add(1);
            List<Integer> pre = res.get(i - 1);
            for (int j = 1; j < i; j++) {
                list.add(pre.get(j - 1) + pre.get(j));
            }
            list.add(1);
            res.add(list);
        }
        return res.get(rowIndex);
    }

    public List<Integer> getRow3(int rowIndex) {
        List<Integer> pre;
        pre = Arrays.asList(1);
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> list = new ArrayList<>(rowIndex + 1);
            list.add(1);
            for (int j = 1; j < i; j++) {
                list.add(pre.get(j - 1) + pre.get(j));
            }
            list.add(1);
            pre = list;
        }
        return pre;
    }

    public static void main(String[] args) {
        PascalsTriangle2 pt = new PascalsTriangle2();
        List<Integer> row = pt.getRow2(3);
        for (int i = 0; i < row.size(); i++) {
            System.out.println(row.get(i));
        }
    }
}
