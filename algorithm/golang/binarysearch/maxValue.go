package binarysearch

// 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
//
//nums.length == n
//nums[i] 是 正整数 ，其中 0 <= i < n
//abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
//nums 中所有元素之和不超过 maxSum
//nums[index] 的值被 最大化
//返回你所构造的数组中的 nums[index] 。
//
//注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
//
//
//
//示例 1：
//
//输入：n = 4, index = 2,  maxSum = 6
//输出：2
//解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
//示例 2：
//
//输入：n = 6, index = 1,  maxSum = 10
//输出：3
//
//
//提示：
//
//1 <= n <= maxSum <= 109
//0 <= index < n
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func maxValue(n int, index int, maxSum int) int {
	// 这里的检测效率太慢，最坏情况是每次都要扫描整个列表
	check := func(num int) bool {
		sum := num
		for i := index - 1; i >= 0; i-- {
			cur := num - (index - i)
			if cur <= 0 {
				cur = 1
			}
			sum += cur
			if sum > maxSum {
				return false
			}
		}
		for i := index + 1; i < n; i++ {
			cur := num - (i - index)
			if cur <= 0 {
				cur = 1
			}
			sum += cur
			if sum > maxSum {
				return false
			}
		}
		return true
	}
	if !check(1) {
		return -1
	}
	l := 1
	r := maxSum
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}

// 过了，整体的时间复杂度O(log(maxSum))
func maxValue2(n int, index int, maxSum int) int {
	// 时间复杂度O(1)，但是这里的数值计算其实可能存在溢出的场景
	check := func(num int) bool {
		sum := num
		if num >= index+1 {
			sum += (num - index + num - 1) * index / 2
		} else {
			sum += index - (num - 1) + num*(num-1)/2
		}
		if sum > maxSum {
			return false
		}
		k := n - index - 1
		if k > 0 {
			if num-1 >= k {
				sum += (num - 1 + num - k) * k / 2
			} else {
				sum += k - (num - 1) + num*(num-1)/2
			}
		}
		return sum <= maxSum
	}
	if !check(1) {
		return -1
	}
	l := 1
	r := maxSum
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
