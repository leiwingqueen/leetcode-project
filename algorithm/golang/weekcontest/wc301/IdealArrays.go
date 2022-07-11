package wc301

//给你两个整数 n 和 maxValue ，用于描述一个 理想数组 。
//
//对于下标从 0 开始、长度为 n 的整数数组 arr ，如果满足以下条件，则认为该数组是一个 理想数组 ：
//
//每个 arr[i] 都是从 1 到 maxValue 范围内的一个值，其中 0 <= i < n 。
//每个 arr[i] 都可以被 arr[i - 1] 整除，其中 0 < i < n 。
//返回长度为 n 的 不同 理想数组的数目。由于答案可能很大，返回对 109 + 7 取余的结果。
//
//
//
//示例 1：
//
//输入：n = 2, maxValue = 5
//输出：10
//解释：存在以下理想数组：
//- 以 1 开头的数组（5 个）：[1,1]、[1,2]、[1,3]、[1,4]、[1,5]
//- 以 2 开头的数组（2 个）：[2,2]、[2,4]
//- 以 3 开头的数组（1 个）：[3,3]
//- 以 4 开头的数组（1 个）：[4,4]
//- 以 5 开头的数组（1 个）：[5,5]
//共计 5 + 2 + 1 + 1 + 1 = 10 个不同理想数组。
//示例 2：
//
//输入：n = 5, maxValue = 3
//输出：11
//解释：存在以下理想数组：
//- 以 1 开头的数组（9 个）：
//   - 不含其他不同值（1 个）：[1,1,1,1,1]
//   - 含一个不同值 2（4 个）：[1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
//   - 含一个不同值 3（4 个）：[1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
//- 以 2 开头的数组（1 个）：[2,2,2,2,2]
//- 以 3 开头的数组（1 个）：[3,3,3,3,3]
//共计 9 + 1 + 1 = 11 个不同理想数组。
//
//
//提示：
//
//2 <= n <= 104
//1 <= maxValue <= 104
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-the-number-of-ideal-arrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//果然OOM
func idealArrays(n int, maxValue int) int {
	mod := 1_000_000_007
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, maxValue+1)
	}
	for i := 1; i <= maxValue; i++ {
		dp[0][i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= maxValue; j++ {
			k := 1
			for j*k <= maxValue {
				dp[i][j] = (dp[i][j] + dp[i-1][j*k]) % mod
				k++
			}
		}
	}
	//统计结果
	res := 0
	for i := 1; i <= maxValue; i++ {
		res = (res + dp[n-1][i]) % mod
	}
	return res
}

//空间优化，但是还是超时
func idealArrays2(n int, maxValue int) int {
	mod := 1_000_000_007
	dp := make([]int, maxValue+1)
	for i := 1; i <= maxValue; i++ {
		dp[i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= maxValue; j++ {
			//从2开始就可以不需要开辟新空间
			k := 2
			for j*k <= maxValue {
				dp[j] = (dp[j] + dp[j*k]) % mod
				k++
			}
		}
	}
	//统计结果
	res := 0
	for i := 1; i <= maxValue; i++ {
		res = (res + dp[i]) % mod
	}
	return res
}

// TODO:数学解法
