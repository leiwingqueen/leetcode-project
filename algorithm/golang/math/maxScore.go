package math

import "sort"

// 给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
//
//在第 i 次操作时（操作编号从 1 开始），你需要：
//
//选择两个元素 x 和 y 。
//获得分数 i * gcd(x, y) 。
//将 x 和 y 从 nums 中删除。
//请你返回 n 次操作后你能获得的分数和最大为多少。
//
//函数 gcd(x, y) 是 x 和 y 的最大公约数。
//
//
//
//示例 1：
//
//输入：nums = [1,2]
//输出：1
//解释：最优操作是：
//(1 * gcd(1, 2)) = 1
//示例 2：
//
//输入：nums = [3,4,6,8]
//输出：11
//解释：最优操作是：
//(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
//示例 3：
//
//输入：nums = [1,2,3,4,5,6]
//输出：14
//解释：最优操作是：
//(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
//
//
//提示：
//
//1 <= n <= 7
//nums.length == 2 * n
//1 <= nums[i] <= 106
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximize-score-after-n-operations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 暴力-超时
func maxScore(nums []int) int {
	n := len(nums)
	res := 0
	var gcd func(a int, b int) int
	var dfs func(idx int)
	dfs = func(idx int) {
		if idx == n {
			i := 0
			arr := make([]int, n/2)
			for i < n {
				arr[i/2] = gcd(nums[i], nums[i+1])
				i += 2
			}
			sort.Ints(arr)
			s := 0
			for j, num := range arr {
				s += (j + 1) * num
			}
			if s > res {
				res = s
			}
			return
		}
		for j := idx; j < n; j++ {
			nums[j], nums[idx] = nums[idx], nums[j]
			dfs(idx + 1)
			nums[j], nums[idx] = nums[idx], nums[j]
		}
	}
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	dfs(0)
	return res
}
