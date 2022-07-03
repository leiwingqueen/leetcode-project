package greedy

import "math"

//给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
//
//注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
//
//
//
//示例 1：
//
//输入：n = 12
//输出：21
//示例 2：
//
//输入：n = 21
//输出：-1
//
//
//提示：
//
//1 <= n <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/next-greater-element-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func nextGreaterElement(n int) int {
	//转化成数组
	size := 10
	nums := make([]int, size)
	idx := 0
	for n > 0 {
		nums[idx] = n % 10
		n /= 10
		idx++
	}
	//找到右边第一个存在更大数字的下标
	size = idx
	idx = 0
	//单调递增
	stack := make([]int, 0)
	for idx < size {
		if len(stack) == 0 || stack[len(stack)-1] <= nums[idx] {
			stack = append(stack, nums[idx])
			idx++
		} else {
			break
		}
	}
	if idx == size {
		return -1
	}
	//把<idx的下标的部分从大到从排序
	tmp := nums[idx]
	i := idx - 1
	for i >= 0 && stack[0] <= tmp {
		nums[i] = stack[0]
		stack = stack[1:]
		i--
	}
	nums[idx] = stack[0]
	stack = stack[1:]
	stack = append([]int{tmp}, stack...)
	for i >= 0 {
		nums[i] = stack[0]
		stack = stack[1:]
		i--
	}
	//转化成整形
	res := 0
	for j := size - 1; j >= 0; j-- {
		res = res*10 + nums[j]
	}
	if res > math.MaxInt32 {
		return -1
	} else {
		return res
	}
}
