package com.liyongquan.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * <p>
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * <p>
 * 你需要原地修改栈。
 * <p>
 * 示例1:
 * <p>
 * 输入：A = [2, 1, 0], B = [], C = []
 * 输出：C = [2, 1, 0]
 * 示例2:
 * <p>
 * 输入：A = [1, 0], B = [], C = []
 * 输出：C = [1, 0]
 * 提示:
 * <p>
 * A中盘子的数目不大于14个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hanota-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Hanota {
    /**
     * 面向结果编程
     *
     * @param A
     * @param B
     * @param C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        for (Integer i : A) {
            C.add(i);
        }
    }

    /**
     * 常规解法。递归解法
     *
     * @param A
     * @param B
     * @param C
     */
    public void hanota2(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);
    }

    private void move(int num, List<Integer> a, List<Integer> b, List<Integer> c) {
        if (num <= 0) {
            return;
        }
        if (num == 1) {
            c.add(a.get(a.size() - 1));
            return;
        }
        move(num - 1, a, c, b);
        c.add(a.get(a.size() - num));
        move(num - 1, b, a, c);
    }


    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(), b = new ArrayList<>(), c = new ArrayList<>();
        a.add(2);
        a.add(1);
        a.add(0);
        Hanota hanota = new Hanota();
        hanota.hanota2(a, b, c);
        for (Integer i : c) {
            System.out.println(i);
        }
    }
}
