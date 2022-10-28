package stack

// 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
//
//由于答案可能很大，因此 返回答案模 10^9 + 7 。
//
//
//
//示例 1：
//
//输入：arr = [3,1,2,4]
//输出：17
//解释：
//子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
//最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
//示例 2：
//
//输入：arr = [11,81,94,43,3]
//输出：444
//
//
//提示：
//
//1 <= arr.length <= 3 * 104
//1 <= arr[i] <= 3 * 104
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sum-of-subarray-minimums
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 暴力解法
// 时间复杂度O(n^2)，勉强通过
func sumSubarrayMins(arr []int) int {
	n := len(arr)
	mod := 1_000_000_007
	res := 0
	for i, num := range arr {
		l := i - 1
		r := i + 1
		// 扫描的时候左区间可以包含重复的数字，右区间不能包含重复的数字，这样扫描出来的区域就不会重复
		// 相当于我们假设每个区域的最小值是区间内的最小值，存在数字相同的情况我们取下标最大的数字
		for l >= 0 && arr[l] >= num {
			l--
		}
		for r < n && arr[r] > num {
			r++
		}
		res = (res + ((r-i)*(i-l)*num)%mod) % mod
	}
	return res
}

// 单调栈优化下找左右边界
func sumSubarrayMins2(arr []int) int {
	n := len(arr)
	mod := 1_000_000_007
	var s []int
	left := make([]int, n)
	for i := 0; i < n; i++ {
		for len(s) > 0 && arr[s[len(s)-1]] >= arr[i] {
			s = s[:len(s)-1]
		}
		left[i] = -1
		if len(s) > 0 {
			left[i] = s[len(s)-1]
		}
		s = append(s, i)
	}
	s = []int{}
	right := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		for len(s) > 0 && arr[s[len(s)-1]] > arr[i] {
			s = s[:len(s)-1]
		}
		right[i] = n
		if len(s) > 0 {
			right[i] = s[len(s)-1]
		}
		s = append(s, i)
	}
	res := 0
	for i, num := range arr {
		l := left[i]
		r := right[i]
		res = (res + ((r-i)*(i-l)*num)%mod) % mod
	}
	return res
}
