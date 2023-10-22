package dp

import "sort"

// 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
//
//一道菜的 「 like-time 系数 」定义为烹饪这道菜结束的时间（包含之前每道菜所花费的时间）乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
//
//返回厨师在准备了一定数量的菜肴后可以获得的最大 like-time 系数 总和。
//
//你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
//
//
//
//示例 1：
//
//输入：satisfaction = [-1,-8,0,5,-9]
//输出：14
//解释：去掉第二道和最后一道菜，最大的 like-time 系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。
//示例 2：
//
//输入：satisfaction = [4,3,2]
//输出：20
//解释：可以按照任意顺序做菜 (2*1 + 3*2 + 4*3 = 20)
//示例 3：
//
//输入：satisfaction = [-1,-4,-5]
//输出：0
//解释：大家都不喜欢这些菜，所以不做任何菜就可以获得最大的 like-time 系数。
//
//
//提示：
//
//n == satisfaction.length
//1 <= n <= 500
//-1000 <= satisfaction[i] <= 1000

// f(n,k)=max{f(n-1,k),f(n-1,k-1)+k*A[n-1])
// 假设f(n,k)为前n个数字中选择k个
// f(0,0)=0
func maxSatisfaction(satisfaction []int) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	sort.Ints(satisfaction)
	n := len(satisfaction)
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, n+1)
	}
	res := 0
	for i := 1; i <= n; i++ {
		for j := 1; j <= i; j++ {
			if i == j {
				dp[i][j] = j*satisfaction[i-1] + dp[i-1][j-1]
			} else {
				dp[i][j] = max(dp[i-1][j], j*satisfaction[i-1]+dp[i-1][j-1])
			}
			res = max(dp[i][j], res)
		}
	}
	return res
}
