package wc364

// 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
//
//你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
//
//如果以下条件满足，我们称这些塔是 美丽 的：
//
//1 <= heights[i] <= maxHeights[i]
//heights 是一个 山状 数组。
//如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山状 数组：
//
//对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
//对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
//请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
//
//
//
//示例 1：
//
//输入：maxHeights = [5,3,4,1,1]
//输出：13
//解释：和最大的美丽塔方案为 heights = [5,3,3,1,1] ，这是一个美丽塔方案，因为：
//- 1 <= heights[i] <= maxHeights[i]
//- heights 是个山状数组，峰值在 i = 0 处。
//13 是所有美丽塔方案中的最大高度和。
//示例 2：
//
//输入：maxHeights = [6,5,3,9,2,7]
//输出：22
//解释： 和最大的美丽塔方案为 heights = [3,3,3,9,2,2] ，这是一个美丽塔方案，因为：
//- 1 <= heights[i] <= maxHeights[i]
//- heights 是个山状数组，峰值在 i = 3 处。
//22 是所有美丽塔方案中的最大高度和。
//示例 3：
//
//输入：maxHeights = [3,2,5,5,2,3]
//输出：18
//解释：和最大的美丽塔方案为 heights = [2,2,5,5,2,2] ，这是一个美丽塔方案，因为：
//- 1 <= heights[i] <= maxHeights[i]
//- heights 是个山状数组，最大值在 i = 2 处。
//注意，在这个方案中，i = 3 也是一个峰值。
//18 是所有美丽塔方案中的最大高度和。
//
//
//提示：
//
//1 <= n == maxHeights <= 105
//1 <= maxHeights[i] <= 109

func maximumSumOfHeights(maxHeights []int) int64 {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(maxHeights)
	cal := func(i int) int64 {
		var total int64
		pre := maxHeights[i]
		for j := i - 1; j >= 0; j-- {
			h := min(maxHeights[j], pre)
			total += int64(h)
			pre = h
		}
		pre = maxHeights[i]
		for j := i + 1; j < n; j++ {
			h := min(maxHeights[j], pre)
			total += int64(h)
			pre = h
		}
		return total + int64(maxHeights[i])
	}
	var res int64
	for i := 0; i < n; i++ {
		h := cal(i)
		if h > res {
			res = h
		}
	}
	return res
}

// 单调栈可以优化
func maximumSumOfHeights2(maxHeights []int) int64 {
	n := len(maxHeights)
	left := make([]int64, n)
	var stack []int
	var sum int64
	for i := 0; i < n; i++ {
		for len(stack) > 0 && maxHeights[stack[len(stack)-1]] >= maxHeights[i] {
			idx := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			next := -1
			if len(stack) > 0 {
				next = stack[len(stack)-1]
			}
			// [next+1,idx]
			sum -= int64(maxHeights[idx]) * int64(idx-next)
		}
		top := -1
		if len(stack) > 0 {
			top = stack[len(stack)-1]
		}
		// [top+1,i]
		sum += int64(i-top) * int64(maxHeights[i])
		stack = append(stack, i)
		left[i] = sum
	}
	stack = stack[:0]
	right := make([]int64, n)
	sum = 0
	for i := n - 1; i >= 0; i-- {
		for len(stack) > 0 && maxHeights[stack[len(stack)-1]] >= maxHeights[i] {
			idx := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			next := n
			if len(stack) > 0 {
				next = stack[len(stack)-1]
			}
			// [idx,next)
			sum -= int64(maxHeights[idx]) * int64(next-idx)
		}
		top := n
		if len(stack) > 0 {
			top = stack[len(stack)-1]
		}
		//[i,top)
		sum += int64(top-i) * int64(maxHeights[i])
		stack = append(stack, i)
		right[i] = sum
	}
	var res int64
	for i := 0; i < n; i++ {
		h := left[i] + right[i] - int64(maxHeights[i])
		if h > res {
			res = h
		}
	}
	return res
}
