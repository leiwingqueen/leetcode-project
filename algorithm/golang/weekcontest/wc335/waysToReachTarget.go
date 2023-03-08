package wc335

// 考试中有 n 种类型的题目。给你一个整数 target 和一个下标从 0 开始的二维整数数组 types ，其中 types[i] = [counti, marksi] 表示第 i 种类型的题目有 counti 道，每道题目对应 marksi 分。
//
//返回你在考试中恰好得到 target 分的方法数。由于答案可能很大，结果需要对 109 +7 取余。
//
//注意，同类型题目无法区分。
//
//比如说，如果有 3 道同类型题目，那么解答第 1 和第 2 道题目与解答第 1 和第 3 道题目或者第 2 和第 3 道题目是相同的。
//
//
//示例 1：
//
//输入：target = 6, types = [[6,1],[3,2],[2,3]]
//输出：7
//解释：要获得 6 分，你可以选择以下七种方法之一：
//- 解决 6 道第 0 种类型的题目：1 + 1 + 1 + 1 + 1 + 1 = 6
//- 解决 4 道第 0 种类型的题目和 1 道第 1 种类型的题目：1 + 1 + 1 + 1 + 2 = 6
//- 解决 2 道第 0 种类型的题目和 2 道第 1 种类型的题目：1 + 1 + 2 + 2 = 6
//- 解决 3 道第 0 种类型的题目和 1 道第 2 种类型的题目：1 + 1 + 1 + 3 = 6
//- 解决 1 道第 0 种类型的题目、1 道第 1 种类型的题目和 1 道第 2 种类型的题目：1 + 2 + 3 = 6
//- 解决 3 道第 1 种类型的题目：2 + 2 + 2 = 6
//- 解决 2 道第 2 种类型的题目：3 + 3 = 6
//示例 2：
//
//输入：target = 5, types = [[50,1],[50,2],[50,5]]
//输出：4
//解释：要获得 5 分，你可以选择以下四种方法之一：
//- 解决 5 道第 0 种类型的题目：1 + 1 + 1 + 1 + 1 = 5
//- 解决 3 道第 0 种类型的题目和 1 道第 1 种类型的题目：1 + 1 + 1 + 2 = 5
//- 解决 1 道第 0 种类型的题目和 2 道第 1 种类型的题目：1 + 2 + 2 = 5
//- 解决 1 道第 2 种类型的题目：5
//示例 3：
//
//输入：target = 18, types = [[6,1],[3,2],[2,3]]
//输出：1
//解释：只有回答所有题目才能获得 18 分。
//
//
//提示：
//
//1 <= target <= 1000
//n == types.length
//1 <= n <= 50
//types[i].length == 2
//1 <= counti, marksi <= 50
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-ways-to-earn-points
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 先来个回溯，必然超时
func waysToReachTarget(target int, types [][]int) int {
	mod := 1_000_000_007
	n := len(types)
	var dfs func(idx int, t int) int
	dfs = func(idx int, t int) int {
		if idx == n {
			if t == 0 {
				return 1
			} else {
				return 0
			}
		}
		res := 0
		for i := 0; i <= types[idx][0]; i++ {
			if i*types[idx][1] > target {
				break
			}
			res = (res + dfs(idx+1, t-i*types[idx][1])) % mod
		}
		return res
	}
	return dfs(0, target)
}

// 好像不难，改写成dp就可以了，背包问题。woo,过啦！
func waysToReachTarget2(target int, types [][]int) int {
	mod := 1_000_000_007
	n := len(types)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, target+1)
	}
	for i := 0; i <= types[0][0]; i++ {
		if i*types[0][1] > target {
			break
		}
		dp[0][i*types[0][1]] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j <= target; j++ {
			for k := 0; k <= types[i][0]; k++ {
				if k*types[i][1] > j {
					break
				}
				dp[i][j] = (dp[i][j] + dp[i-1][j-k*types[i][1]]) % mod
			}
		}
	}
	return dp[n-1][target]
}
