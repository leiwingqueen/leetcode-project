package dp

import "math"

//给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以
//决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
//
// 注意，删除一个元素后，子数组 不能为空。
//
//
//
// 示例 1：
//
//
//输入：arr = [1,-2,0,3]
//输出：4
//解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
//
// 示例 2：
//
//
//输入：arr = [1,-2,-2,3]
//输出：3
//解释：我们直接选出 [3]，这就是最大和。
//
//
// 示例 3：
//
//
//输入：arr = [-1,-1,-1,-1]
//输出：-1
//解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
//     我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
//
//
//
//
// 提示：
//
//
//
// 1 <= arr.length <= 10⁵
// -10⁴ <= arr[i] <= 10⁴
//
//
// Related Topics 数组 动态规划 👍 226 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
// 先不考虑删除的情况
func maximumSum(arr []int) int {
	n := len(arr)
	res := math.MinInt
	minSum := 0
	sum := 0
	for i := 0; i < n; i++ {
		sum += arr[i]
		if sum-minSum > res {
			res = sum - minSum
		}
		if sum < minSum {
			minSum = sum
		}
	}
	return res
}

//leetcode submit region end(Prohibit modification and deletion)

// 这个dp方程不好想啊
// dp[i][0]=max{dp[i-1][0]+arr[i],arr[i]}
// dp[i][1]=max{dp[i-1][1]+arr[i],dp[i-1][0]}
// 初始化 dp[0][0]=arr[0],dp[0][1]=0

// 通过，击败50%的用户。这里还可以有一个小优化，因为每次迭代只需要用到上一次的结果，空间还可以继续压缩
func maximumSum2(arr []int) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	n := len(arr)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 2)
	}
	dp[0][0] = arr[0]
	dp[0][1] = 0
	res := arr[0]
	for i := 1; i < n; i++ {
		dp[i][0] = max(dp[i-1][0]+arr[i], arr[i])
		res = max(dp[i][0], res)
		dp[i][1] = max(dp[i-1][1]+arr[i], dp[i-1][0])
		res = max(dp[i][1], res)
	}
	return res
}

func maximumSum3(arr []int) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	n := len(arr)
	dp0, dp1 := arr[0], 0
	res := arr[0]
	for i := 1; i < n; i++ {
		dp0_ := max(dp0+arr[i], arr[i])
		res = max(dp0_, res)
		dp1_ := max(dp1+arr[i], dp0)
		res = max(dp1_, res)
		dp0, dp1 = dp0_, dp1_
	}
	return res
}
