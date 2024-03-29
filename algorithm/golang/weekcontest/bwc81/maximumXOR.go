package bwc81

//给你一个下标从 0 开始的整数数组 nums 。一次操作中，选择 任意 非负整数 x 和一个下标 i ，更新 nums[i] 为 nums[i] AND (nums[i] XOR x) 。
//
//注意，AND 是逐位与运算，XOR 是逐位异或运算。
//
//请你执行 任意次 更新操作，并返回 nums 中所有元素 最大 逐位异或和。
//
//
//
//示例 1：
//
//输入：nums = [3,2,4,6]
//输出：7
//解释：选择 x = 4 和 i = 3 进行操作，num[3] = 6 AND (6 XOR 4) = 6 AND 2 = 2 。
//现在，nums = [3, 2, 4, 2] 且所有元素逐位异或得到 3 XOR 2 XOR 4 XOR 2 = 7 。
//可知 7 是能得到的最大逐位异或和。
//注意，其他操作可能也能得到逐位异或和 7 。
//示例 2：
//
//输入：nums = [1,2,3,9,2]
//输出：11
//解释：执行 0 次操作。
//所有元素的逐位异或和为 1 XOR 2 XOR 3 XOR 9 XOR 2 = 11 。
//可知 11 是能得到的最大逐位异或和。
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 108
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-xor-after-operations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 当一个数字的某位为0的时候，nums[i] AND (nums[i] XOR x) 操作后必然还是为0
// 当一个数字的某位为1的时候, nums[i] AND (nums[i] XOR x) 操作后可以为1或者0
// 我们需要让所有数字的异或和最大，则为0的位我们是不能控制的，但是1的位我们可以通过运算控制为1或者0。
// 因此假设某一个位上只要有一个数字的位不为0，那么我们总能让这一位最终异或为1。
// 则我们要求的是，某一位上位为0的交集分别是那些。在确定这些0的位后，我们把剩余的位全部置成1就是我们最终的解了
func maximumXOR(nums []int) int {
	//全1
	res := ^0
	//每个数字按位去反，再&，这么会得到所有数字的0的位的交集
	for _, num := range nums {
		res &= ^num
	}
	//最终取反就是最后的解
	return ^res
}
