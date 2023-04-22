package dp

// 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
//
//回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，且 0 <= i1 < i2 < ... < ik <= nums.length - 1。并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
//
//
//
//示例 1：
//
//输入：nums = [3,6,9,12]
//输出：4
//解释：
//整个数组是公差为 3 的等差数列。
//示例 2：
//
//输入：nums = [9,4,7,2,10]
//输出：3
//解释：
//最长的等差子序列是 [4,7,10]。
//示例 3：
//
//输入：nums = [20,1,15,3,10,5,8]
//输出：4
//解释：
//最长的等差子序列是 [20,15,10,5]。
//
//
//提示：
//
//2 <= nums.length <= 1000
//0 <= nums[i] <= 500
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-arithmetic-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// dfs，超时
func longestArithSeqLength(nums []int) int {
	// 选择j,i作为最后两个数字的等差数列，其中j<i
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		// 只需要找到跟j最近的符合条件的数字，这个一定是最优解
		for k := j - 1; k >= 0; k-- {
			if nums[k]-nums[j] == nums[j]-nums[i] {
				return dfs(j, k) + 1
			}
		}
		return 2
	}
	n := len(nums)
	// 最后穷举最后的两位数字即可
	res := 0
	for i := n - 1; i > 0; i-- {
		for j := i - 1; j >= 0; j-- {
			sub := dfs(i, j)
			if sub > res {
				res = sub
			}
		}
	}
	return res
}

// 上面超时的问题在于没有把重复解的问题处理。我们改成dp的写法，勉强通过
func longestArithSeqLength2(nums []int) int {
	n := len(nums)
	// dp[i][j]表示最后两位数位下标i,j。其中j<i，dp迭代的过程可以参考上面
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
	}
	for i := 1; i < n; i++ {
		dp[i][0] = 2
	}
	for i := 2; i < n; i++ {
		for j := 1; j < i; j++ {
			// 这个循环还有优化的空间
			k := j - 1
			for ; k >= 0; k-- {
				if nums[k]-nums[j] == nums[j]-nums[i] {
					break
				}
			}
			if k >= 0 {
				dp[i][j] = dp[j][k] + 1
			} else {
				dp[i][j] = 2
			}
		}
	}
	// 最后穷举最后的两位数字即可
	res := 0
	for i := n - 1; i > 0; i-- {
		for j := i - 1; j >= 0; j-- {
			if dp[i][j] > res {
				res = dp[i][j]
			}
		}
	}
	return res
}

// 在上面的基础上优化，我们只需要记录某个值最后出现的位置，第三层循环也可以优化掉
func longestArithSeqLength3(nums []int) int {
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
	}
	dp[1][0] = 2
	for i := 2; i < n; i++ {
		// 记录某个值的最后出现的位置
		mp := make(map[int]int)
		for j := 0; j < i; j++ {
			k, exist := mp[nums[j]-(nums[i]-nums[j])]
			if exist {
				dp[i][j] = dp[j][k] + 1
			} else {
				dp[i][j] = 2
			}
			mp[nums[j]] = j
		}
	}
	res := 0
	for i := n - 1; i > 0; i-- {
		for j := i - 1; j >= 0; j-- {
			if dp[i][j] > res {
				res = dp[i][j]
			}
		}
	}
	return res
}
