package dp

// 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
//
//返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
//
//
//
//示例 1：
//
//输入：arr = [1,15,7,9,2,5,10], k = 3
//输出：84
//解释：数组变为 [15,15,15,9,10,10,10]
//示例 2：
//
//输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
//输出：83
//示例 3：
//
//输入：arr = [1], k = 1
//输出：1
//
//
//提示：
//
//1 <= arr.length <= 500
//0 <= arr[i] <= 109
//1 <= k <= arr.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/partition-array-for-maximum-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func maxSumAfterPartitioning(arr []int, k int) int {
	n := len(arr)
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		mx := 0
		for j := 1; j <= k && i-j >= 0; j++ {
			if arr[i-j] > mx {
				mx = arr[i-j]
			}
			sub := dp[i-j] + mx*j
			if sub > dp[i] {
				dp[i] = sub
			}
		}
	}
	return dp[n]
}
