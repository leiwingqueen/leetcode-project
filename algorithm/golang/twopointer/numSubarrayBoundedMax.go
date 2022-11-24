package twopointer

// 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
//
//生成的测试用例保证结果符合 32-bit 整数范围。
//
//
//
//示例 1：
//
//输入：nums = [2,1,4,3], left = 2, right = 3
//输出：3
//解释：满足条件的三个子数组：[2], [2, 1], [3]
//示例 2：
//
//输入：nums = [2,9,2,5,6], left = 2, right = 8
//输出：7
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//0 <= left <= right <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 不通过，题目理解错了，不是所有元素，而是最大元素
func numSubarrayBoundedMax(nums []int, left int, right int) int {
	l := 0
	r := 0
	n := len(nums)
	res := 0
	for r < n {
		for l < n && (nums[l] < left || nums[l] > right) {
			l++
		}
		if l == n {
			break
		}
		r = l
		for r < n && nums[r] >= left && nums[r] <= right {
			r++
		}
		res += (1 + r - l) * (r - l) / 2
		l = r
	}
	return res
}

// 单调栈，但是写起来还真的有点技巧
func numSubarrayBoundedMax2(nums []int, left int, right int) int {
	n := len(nums)
	// 找出以每个点为最大值的左右边界
	ll := make([]int, n)
	rr := make([]int, n)
	stack := make([]int, 0)
	for i, num := range nums {
		for len(stack) > 0 && nums[stack[len(stack)-1]] <= num {
			stack = stack[0 : len(stack)-1]
		}
		ll[i] = -1
		if len(stack) > 0 {
			ll[i] = stack[len(stack)-1]
		}
		stack = append(stack, i)
	}
	stack = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stack) > 0 && nums[stack[len(stack)-1]] < nums[i] {
			stack = stack[0 : len(stack)-1]
		}
		rr[i] = n
		if len(stack) > 0 {
			rr[i] = stack[len(stack)-1]
		}
		stack = append(stack, i)
	}
	res := 0
	for i, num := range nums {
		if num >= left && num <= right {
			// (ll[i],rr[i])
			res += (i - ll[i]) * (rr[i] - i)
		}
	}
	return res
}
