package dp

import (
	"leetcode-go/util"
	"math"
)

// 你需要制定一份 d 天的工作计划表。工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。
//
//你每天 至少 需要完成一项任务。工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。
//
//给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。第 i 项工作的难度是 jobDifficulty[i]。
//
//返回整个工作计划的 最小难度 。如果无法制定工作计划，则返回 -1 。
//
//
//
//示例 1：
//
//
//
//输入：jobDifficulty = [6,5,4,3,2,1], d = 2
//输出：7
//解释：第一天，您可以完成前 5 项工作，总难度 = 6.
//第二天，您可以完成最后一项工作，总难度 = 1.
//计划表的难度 = 6 + 1 = 7
//示例 2：
//
//输入：jobDifficulty = [9,9,9], d = 4
//输出：-1
//解释：就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
//示例 3：
//
//输入：jobDifficulty = [1,1,1], d = 3
//输出：3
//解释：工作计划为每天一项工作，总难度为 3 。
//示例 4：
//
//输入：jobDifficulty = [7,1,7,1,7,1], d = 3
//输出：15
//示例 5：
//
//输入：jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
//输出：843
//
//
//提示：
//
//1 <= jobDifficulty.length <= 300
//0 <= jobDifficulty[i] <= 1000
//1 <= d <= 10
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func minDifficulty(jobDifficulty []int, d int) int {
	n := len(jobDifficulty)
	if d > n {
		return -1
	}
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, d)
	}
	// 初始化
	mx := 0
	for i := 0; i < n; i++ {
		dp[i][0] = util.Max(mx, jobDifficulty[i])
	}
	for i := 1; i < n; i++ {
		// 这里反过来遍历，可以顺便计算对应[j,i]的最大值
		mxArr := make([]int, i+1)
		mx2 := 0
		for j := util.Min(i, d-1); j > 0; j-- {
			mxArr[j] = util.Max(mx2, jobDifficulty[j])
			// i-l>=j-1 ==> l<=i-j+1
			dp[i][j] = math.MaxInt32
			for l := 1; l <= i-j+1; l++ {
				// 求[i-l+1,i]的最大值，由于l<=i-j+1,实际上i-l+1>=j
				sub := dp[i-l][j-1] + mxArr[i-l+1]
				dp[i][j] = util.Min(dp[i][j], sub)
			}
		}
	}
	return dp[n-1][d-1]
}
