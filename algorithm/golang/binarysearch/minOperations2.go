package binarysearch

// 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
//
//如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
//
//
//
//示例 1：
//
//输入：nums = [1,1,4,2,3], x = 5
//输出：2
//解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
//示例 2：
//
//输入：nums = [5,6,7,8,9], x = 4
//输出：-1
//示例 3：
//
//输入：nums = [3,2,20,1,1,3], x = 10
//输出：5
//解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 104
//1 <= x <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 暴力解法O(n^2)，超时
func minOperations2(nums []int, x int) int {
	n := len(nums)
	preSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		preSum[i+1] = preSum[i] + nums[i]
	}
	check := func(k int) bool {
		for i := 0; i <= k; i++ {
			j := k - i
			// 选择右边的数字,[n-j,,n)
			sum := preSum[i] + preSum[n] - preSum[n-j]
			if sum == x {
				return true
			}
		}
		return false
	}
	for i := 0; i <= n; i++ {
		if check(i) {
			return i
		}
	}
	return -1
}

func minOperations3(nums []int, x int) int {
	n := len(nums)
	// 统计后缀
	preSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		preSum[i+1] = preSum[i] + nums[n-1-i]
	}
	search := func(sum int) int {
		if preSum[n] < sum {
			return -1
		}
		l := 0
		r := n
		for l < r {
			mid := l + (r-l)/2
			if preSum[mid] >= sum {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return l
	}
	res := -1
	lSum := 0
	for i := 0; i <= n; i++ {
		if lSum > x {
			return res
		}
		if lSum == x {
			if res < 0 || i < res {
				res = i
			}
			return res
		}
		rSum := x - lSum
		j := search(rSum)
		if j >= 0 && i+j <= n && preSum[j] == rSum {
			if res < 0 || i+j < res {
				res = i + j
			}
		}
		if i < n {
			lSum += nums[i]
		}
	}
	return res
}
