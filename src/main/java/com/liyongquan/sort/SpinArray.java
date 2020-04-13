package com.liyongquan.sort;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SpinArray {
    /**
     * 二分查找
     * 思路是找到一条分割线，让左右两边都是自增
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        return find(numbers,0,numbers.length-1);
    }

    private int find(int[] num,int start,int end){
        //1个或2个特殊处理
        if (end-start<=1) {
            return num[start]<num[end]?num[start]:num[end];
        }
        int middle=(end+start)/2;
        if(num[middle-1]>=num[start]&&num[middle]<=num[end]){
            return num[middle];
        }
        if (num[middle]>num[end]) {
            return find(num, middle+1,end);
        }
        return find(num,start,middle);
    }

    public static void main(String[] args) {
        SpinArray spinArray=new SpinArray();
        //int i = spinArray.minArray(new int[]{3, 4, 5, 1, 2});
        int i = spinArray.minArray(new int[]{1,3,5});
        System.out.println(i);
    }
}
