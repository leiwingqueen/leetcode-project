package wc390

// 给你一个正整数 k 。最初，你有一个数组 nums = [1] 。
//
//你可以对数组执行以下 任意 操作 任意 次数（可能为零）：
//
//选择数组中的任何一个元素，然后将它的值 增加 1 。
//复制数组中的任何一个元素，然后将它附加到数组的末尾。
//返回使得最终数组元素之 和 大于或等于 k 所需的 最少 操作次数。
//
//
//
//示例 1：
//
//输入：k = 11
//
//输出：5
//
//解释：
//
//可以对数组 nums = [1] 执行以下操作：
//
//将元素的值增加 1 三次。结果数组为 nums = [4] 。
//复制元素两次。结果数组为 nums = [4,4,4] 。
//最终数组的和为 4 + 4 + 4 = 12 ，大于等于 k = 11 。
//执行的总操作次数为 3 + 2 = 5 。
//
//示例 2：
//
//输入：k = 1
//
//输出：0
//
//解释：
//
//原始数组的和已经大于等于 1 ，因此不需要执行操作。
//
//
//
//提示：
//
//1 <= k <= 105

// 朴素dfs
func minOperations(k int) int {
	var dfs func(sum int, mx int) int
	dfs = func(sum int, mx int) int {
		if sum >= k {
			return 0
		}
		// 加1操作
		s1 := dfs(sum+1, mx+1) + 1
		// 复制mx
		s2 := dfs(sum+mx, mx) + 1
		if s1 < s2 {
			return s1
		} else {
			return s2
		}
	}
	return dfs(1, 1)
}

// 这种是否都是先增加，然后多次复制？
func minOperations2(k int) int {
	res := k
	for i := 1; i <= k; i++ {
		c := (k + i - 1) / i
		t := c - 1 + i - 1
		if t < res {
			res = t
		}
	}
	return res
}
