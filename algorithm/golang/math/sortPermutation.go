package math

// 给你一个长度为 n 的整数数组 nums，其中 nums 是范围 [0..n - 1] 内所有数字的一个 排列 。
//
//你可以在满足条件 nums[i] AND nums[j] == k 的情况下交换下标 i 和 j 的元素，其中 AND 表示按位与操作，k 是一个非负整数。
//
//返回可以使数组按 非递减 顺序排序的最大值 k（允许进行任意次这样的交换）。如果 nums 已经是有序的，返回 0。
//
//排列 是数组所有元素的一种重新排列。
//
//
//
//示例 1：
//
//输入：nums = [0,3,2,1]
//
//输出：1
//
//解释：
//
//选择 k = 1。交换 nums[1] = 3 和 nums[3] = 1，因为 nums[1] AND nums[3] == 1，从而得到一个排序后的排列：[0, 1, 2, 3]。
//
//示例 2：
//
//输入：nums = [0,1,3,2]
//
//输出：2
//
//解释：
//
//选择 k = 2。交换 nums[2] = 3 和 nums[3] = 2，因为 nums[2] AND nums[3] == 2，从而得到一个排序后的排列：[0, 1, 2, 3]。
//
//示例 3：
//
//输入：nums = [3,2,1,0]
//
//输出：0
//
//解释：
//
//只有当 k = 0 时，才能进行排序，因为没有更大的 k 能够满足 nums[i] AND nums[j] == k 的交换条件。
//
//
//
//提示：
//
//1 <= n == nums.length <= 105
//0 <= nums[i] <= n - 1
//nums 是从 0 到 n - 1 的一个排列。

// 计算过程很简单，但是证明有点巧妙
func sortPermutation(nums []int) int {
	bitCount := func(num int) int {
		cnt := 0
		for num > 0 {
			cnt++
			num >>= 1
		}
		return cnt
	}
	andSum := (1 << bitCount(len(nums)-1)) - 1
	flag := true
	for i, num := range nums {
		if i != num {
			andSum &= num
			flag = false
		}
	}
	if flag {
		return 0
	} else {
		return andSum
	}
}
