package com.liyongquan.array;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SlidingWindow {
    /**
     * 遍历的方式，不做任何优化
     * 时间复杂度为O(n*k),n为元素的数量
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length<=0) {
            return new int[0];
        }
        int[] result=new int[nums.length-k+1];
        for (int i = 0; i < nums.length-k+1; i++) {
            scanK(nums, result, i, i+k);
        }
        return result;
    }

    /**
     * 事实上，我们可以利用上一次的计算结果来适当地减枝。
     * if num[i+k-1]>f(i-1),则f(i)=num[i+k-1]
     * else if num[i-1]!=f(i-1),则f(i)=f(i-1)
     * else 遍历k个数字
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length<=0) {
            return new int[0];
        }
        int[] result=new int[nums.length-k+1];
        for (int i = 0; i < nums.length-k+1; i++) {
            if(i==0) {
                scanK(nums, result, i, i + k);
            }else{
                if (nums[i+k-1]>result[i-1]) {
                    result[i]=nums[i+k-1];
                }else if(nums[i-1]!=result[i-1]){
                    result[i]=result[i-1];
                }else{
                    scanK(nums, result, i, i + k);
                }
            }
        }
        return result;
    }

    private void scanK(int[] nums, int[] result, int i, int i1) {
        int max = nums[i];
        for (int j = i + 1; j < i1; j++) {
            if (nums[j] > max) {
                max = nums[j];
            }
        }
        result[i] = max;
    }

    //TODO:还有一种双端队列的解法
}
