package com.liyongquan.array;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，
 * 所有偶数位于数组的后半部分。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TiaozhengShuzu {
    public int[] exchange(int[] nums) {
        if (nums.length<=1) {
            return nums;
        }
        int left=0;
        int right=nums.length-1;
        while (left<right){
            //从左边开始找到第一个偶数
            while (left<right&&nums[left]%2==1){
                left++;
            }
            //从右边开始找到第一个奇数
            while (left<right&&nums[right]%2==0){
                right--;
            }
            if (left==right) {
                break;
            }
            int temp=nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
        return nums;
    }

    public static void main(String[] args) {
        TiaozhengShuzu tiaozhengShuzu=new TiaozhengShuzu();
        int[] exchange = tiaozhengShuzu.exchange(new int[]{1, 2, 3, 4});
        for (int i = 0; i < exchange.length; i++) {
            System.out.println(exchange[i]+",");
        }
    }
}
