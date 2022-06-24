package dp

const MaxSum = 1000
const MaxLen = 2*MaxSum + 1

func findTargetSumWays(nums []int, target int) int {
	len := len(nums)
	dp := make([][MaxLen]int, len)
	//初始化
	dp[0][nums[0]+MaxSum]++
	dp[0][-nums[0]+MaxSum]++
	//dp迭代
	for i := 1; i < len; i++ {
		for j := 0; j < MaxLen; j++ {
			if j+nums[i] >= 0 && j+nums[i] < MaxLen {
				dp[i][j] += dp[i-1][j+nums[i]]
			}
			if j-nums[i] >= 0 && j-nums[i] < MaxLen {
				dp[i][j] += dp[i-1][j-nums[i]]
			}
		}
	}
	return dp[len-1][target+MaxSum]
}
