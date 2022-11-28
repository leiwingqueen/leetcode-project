package dp

// 813. 最大平均值和的分组
// 给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。
//
//注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
//
//返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。
//
//
//
//示例 1:
//
//输入: nums = [9,1,2,3,9], k = 3
//输出: 20.00000
//解释:
//nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
//我们也可以把 nums 分成[9, 1], [2], [3, 9].
//这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
//示例 2:
//
//输入: nums = [1,2,3,4,5,6,7], k = 4
//输出: 20.50000
//
//
//提示:
//
//1 <= nums.length <= 100
//1 <= nums[i] <= 104
//1 <= k <= nums.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-sum-of-averages
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	preSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		preSum[i+1] = preSum[i] + nums[i]
	}
	dp := make([][]float64, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]float64, k)
	}
	for i := 0; i < k; i++ {
		dp[i][i] = float64(preSum[i+1])
	}
	dp[0][0] = float64(nums[0])
	for i := 1; i < n; i++ {
		// j<i
		dp[i][0] = float64(preSum[i+1]) / float64(i+1)
		for j := 1; j < k && j < i; j++ {
			for l := j - 1; l < i; l++ {
				// [l+1,i]
				sub := dp[l][j-1] + float64(preSum[i+1]-preSum[l+1])/float64(i-l)
				if sub > dp[i][j] {
					dp[i][j] = sub
				}
			}
		}
	}
	var res float64
	for i := 0; i < k; i++ {
		if dp[n-1][i] > res {
			res = dp[n-1][i]
		}
	}
	return res
}
