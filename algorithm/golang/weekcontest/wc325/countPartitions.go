package wc325

// 给你一个正整数数组 nums 和一个整数 k 。
//
//分区 的定义是：将数组划分成两个有序的 组 ，并满足每个元素 恰好 存在于 某一个 组中。如果分区中每个组的元素和都大于等于 k ，则认为分区是一个好分区。
//
//返回 不同 的好分区的数目。由于答案可能很大，请返回对 109 + 7 取余 后的结果。
//
//如果在两个分区中，存在某个元素 nums[i] 被分在不同的组中，则认为这两个分区不同。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3,4], k = 4
//输出：6
//解释：好分区的情况是 ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) 和 ([4], [1,2,3]) 。
//示例 2：
//
//输入：nums = [3,3,3], k = 4
//输出：0
//解释：数组中不存在好分区。
//示例 3：
//
//输入：nums = [6,6], k = 2
//输出：2
//解释：可以将 nums[0] 放入第一个分区或第二个分区中。
//好分区的情况是 ([6], [6]) 和 ([6], [6]) 。
//
//
//提示：
//
//1 <= nums.length, k <= 1000
//1 <= nums[i] <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-great-partitions
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func countPartitions(nums []int, k int) int {
	sum := 0
	for _, num := range nums {
		sum += num
	}
	if sum < 2*k {
		return 0
	}
	mod := 1_000_000_009
	n := len(nums)
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, k+1)
	}
	// 不选也是一个方案
	for i := 0; i <= k; i++ {
		dp[0][i] = 1
	}
	total := 1
	for i := 1; i <= n; i++ {
		total = (total * 2) % mod
		for j := 0; j <= k; j++ {
			dp[i][j] = dp[i-1][j]
			if nums[i-1] <= j {
				dp[i][j] = (dp[i][j] + dp[i-1][j-nums[i-1]]) % mod
			}
		}
	}
	return (total - (dp[n][k]*2)%mod + mod) % mod
}
