package dp

// 我们定义 arr 是 山形数组 当且仅当它满足：
//
//arr.length >= 3
//存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
//arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//给你整数数组 nums​ ，请你返回将 nums 变成 山形状数组 的​ 最少 删除次数。
//
//
//
//示例 1：
//
//输入：nums = [1,3,1]
//输出：0
//解释：数组本身就是山形数组，所以我们不需要删除任何元素。
//示例 2：
//
//输入：nums = [2,1,1,5,6,2,3,1]
//输出：3
//解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
//
//
//提示：
//
//3 <= nums.length <= 1000
//1 <= nums[i] <= 109
//题目保证 nums 删除一些元素后一定能得到山形数组。

// 经典DP问题
func minimumMountainRemovals(nums []int) int {
	n := len(nums)
	// 以某个下标为最大值的，最小删除的递增数组和递减数组
	f1, f2 := make([]int, n), make([]int, n)
	for i := 1; i < n; i++ {
		// 最坏情况，前面的全部删掉
		f1[i] = i
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] && f1[j]+i-j-1 < f1[i] {
				f1[i] = f1[j] + i - j - 1
			}
		}
	}
	for i := n - 2; i >= 0; i-- {
		f2[i] = n - i - 1
		for j := i + 1; j < n; j++ {
			if nums[j] < nums[i] && f2[j]+j-i-1 < f2[i] {
				f2[i] = f2[j] + j - i - 1
			}
		}
	}
	// 然后尝试以每个下标计算最小的删除
	res := n
	for i := 1; i < n-1; i++ {
		// 需要保证不是全删的场景
		if f1[i] < i && f2[i] < n-i-1 && f1[i]+f2[i] < res {
			res = f1[i] + f2[i]
		}
	}
	return res
}
