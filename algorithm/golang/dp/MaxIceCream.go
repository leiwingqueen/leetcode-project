package dp

import "sort"

//夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
//
//商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
//
//给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
//
//注意：Tony 可以按任意顺序购买雪糕。
//
// 
//
//示例 1：
//
//输入：costs = [1,3,2,4,1], coins = 7
//输出：4
//解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
//示例 2：
//
//输入：costs = [10,6,8,7,7,8], coins = 5
//输出：0
//解释：Tony 没有足够的钱买任何一支雪糕。
//示例 3：
//
//输入：costs = [1,6,3,1,2,5], coins = 20
//输出：6
//解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
// 
//
//提示：
//
//costs.length == n
//1 <= n <= 105
//1 <= costs[i] <= 105
//1 <= coins <= 108
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//典型的01背包，但是数据量的问题会导致超时
func maxIceCream(costs []int, coins int) int {
	len := len(costs)
	dp := make([][]int, len)
	for i := 0; i < len; i++ {
		dp[i] = make([]int, coins+1)
	}
	//初始化
	for i := 0; i <= coins; i++ {
		if costs[0] <= i {
			dp[0][i] = 1
		}
	}
	//dp迭代
	for i := 1; i < len; i++ {
		for j := 0; j <= coins; j++ {
			dp[i][j] = dp[i-1][j]
			if j >= costs[i] && dp[i-1][j-costs[i]]+1 >= dp[i][j] {
				dp[i][j] = dp[i-1][j-costs[i]] + 1
			}
		}
	}
	return dp[len-1][coins]
}

//其实是个贪心算法
func maxIceCream2(costs []int, coins int) int {
	sort.Ints(costs)
	cnt := 0
	sum := 0
	for _, c := range costs {
		sum += c
		if sum <= coins {
			cnt++
		} else {
			break
		}
	}
	return cnt
}
