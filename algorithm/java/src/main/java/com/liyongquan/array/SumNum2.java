package com.liyongquan.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SumNum2 {
    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> result=new ArrayList<>();
        for (int i = 1; i <= target - 1; i++) {
            List<Integer> r=new ArrayList<>();
            int sum=0;
            for (int j = i; j <= target-1; j++) {
                sum+=j;
                if (sum==target) {
                    r.add(j);
                    result.add(r);
                }else if(sum<target){
                    r.add(j);
                }else{
                    break;
                }
            }
        }
        if (result.size()==0) {
            return new int[0][0];
        }
        int[][] result2=new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            List<Integer> list = result.get(i);
            int[] r2 = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                Integer integer = list.get(j);
                r2[j]=integer;
            }
            result2[i]=r2;
        }
        return result2;
    }

    public static void main(String[] args) {
        SumNum2 sumNum2=new SumNum2();
        int[][] continuousSequence = sumNum2.findContinuousSequence(9);
        for (int i = 0; i < continuousSequence.length; i++) {
            int[] ints = continuousSequence[i];
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j]+",");
            }
            System.out.println();
        }
    }
}
