package bwc143

import "sort"

// 给你一个整数数组 nums 和两个整数 k 和 numOperations 。
//
//你必须对 nums 执行 操作  numOperations 次。每次操作中，你可以：
//
//选择一个下标 i ，它在之前的操作中 没有 被选择过。
//将 nums[i] 增加范围 [-k, k] 中的一个整数。
//在执行完所有操作以后，请你返回 nums 中出现 频率最高 元素的出现次数。
//
//一个元素 x 的 频率 指的是它在数组中出现的次数。
//
//
//
//示例 1：
//
//输入：nums = [1,4,5], k = 1, numOperations = 2
//
//输出：2
//
//解释：
//
//通过以下操作得到最高频率 2 ：
//
//将 nums[1] 增加 0 ，nums 变为 [1, 4, 5] 。
//将 nums[2] 增加 -1 ，nums 变为 [1, 4, 4] 。
//示例 2：
//
//输入：nums = [5,11,20,20], k = 5, numOperations = 1
//
//输出：2
//
//解释：
//
//通过以下操作得到最高频率 2 ：
//
//将 nums[1] 增加 0 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//0 <= k <= 105
//0 <= numOperations <= nums.length

// 超时
func maxFrequency(nums []int, k int, numOperations int) int {
	n := len(nums)
	sort.Ints(nums)
	cal := func(num int) int {
		// 二分找到左边界
		l := sort.Search(n, func(i int) bool {
			return nums[i]+k >= num
		})
		if l == n || nums[l]-k > num {
			return 0
		}
		// 二分找到对应的右边界
		r := sort.Search(n, func(i int) bool {
			return nums[i]-k > num
		})
		// 从[l,r)里面查看最大满足的数量，核心问题是这里应该怎么优化
		cnt := 0
		for i := l; i < r; i++ {
			if nums[i] == num {
				// 不需要调整的数量
				cnt++
			}
		}
		return cnt + min(r-l-cnt, numOperations)
	}
	l, r := nums[0], nums[n-1]
	res := 0
	for i := l; i <= r; i++ {
		res = max(res, cal(i))
	}
	return res
}

func maxFrequency2(nums []int, k int, numOperations int) int {
	n := len(nums)
	sort.Ints(nums)
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	cal := func(num int) int {
		// 二分找到左边界
		l := sort.Search(n, func(i int) bool {
			return nums[i]+k >= num
		})
		if l == n || nums[l]-k > num {
			return 0
		}
		// 二分找到对应的右边界
		r := sort.Search(n, func(i int) bool {
			return nums[i]-k > num
		})
		return counter[num] + min(r-l-counter[num], numOperations)
	}
	l, r := nums[0], nums[n-1]
	res := 0
	for i := l; i <= r; i++ {
		res = max(res, cal(i))
	}
	return res
}

func maxFrequency3(nums []int, k int, numOperations int) int {
	n := len(nums)
	sort.Ints(nums)
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	cal := func(num int) int {
		// 二分找到左边界
		l := sort.Search(n, func(i int) bool {
			return nums[i]+k >= num
		})
		if l == n || nums[l]-k > num {
			return 0
		}
		// 二分找到对应的右边界
		r := sort.Search(n, func(i int) bool {
			return nums[i]-k > num
		})
		return counter[num] + min(r-l-counter[num], numOperations)
	}
	res := 0
	for i := 0; i < n; i++ {
		res = max(res, cal(nums[i-k]), cal(nums[i]), cal(nums[i+k]))
	}
	return res
}
