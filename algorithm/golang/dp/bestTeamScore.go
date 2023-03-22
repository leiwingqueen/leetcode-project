package dp

import "sort"

// 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
//
//然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
//
//给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
//
//
//
//示例 1：
//
//输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
//输出：34
//解释：你可以选中所有球员。
//示例 2：
//
//输入：scores = [4,5,6,5], ages = [2,1,2,1]
//输出：16
//解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
//示例 3：
//
//输入：scores = [1,2,3,5], ages = [8,9,10,1]
//输出：6
//解释：最佳的选择是前 3 名球员。
//
//
//提示：
//
//1 <= scores.length, ages.length <= 1000
//scores.length == ages.length
//1 <= scores[i] <= 106
//1 <= ages[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/best-team-with-no-conflicts
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 时间复杂度O(n^2)
func bestTeamScore(scores []int, ages []int) int {
	n := len(scores)
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		score, age := scores[i], ages[i]
		arr[i] = []int{score, age}
	}
	sort.Slice(arr, func(i int, j int) bool {
		if arr[i][1] != arr[j][1] {
			return arr[i][1] < arr[j][1]
		} else {
			return arr[i][0] < arr[j][0]
		}
	})
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
	}
	// 初始化n=0
	for i := 0; i < n; i++ {
		if arr[i][0] >= arr[0][0] {
			dp[0][i] = arr[0][0]
		}
	}
	// dp迭代
	for i := 1; i < n; i++ {
		for j := 0; j < n; j++ {
			// 不选
			dp[i][j] = dp[i-1][j]
			// 选择第i个
			if arr[i][0] <= arr[j][0] {
				sub := dp[i-1][i] + arr[i][0]
				if sub > dp[i][j] {
					dp[i][j] = sub
				}
			}
		}
	}
	// 选择最后一行的最大值
	res := 0
	for i := 0; i < n; i++ {
		if dp[n-1][i] > res {
			res = dp[n-1][i]
		}
	}
	return res
}
