package bwc115

// 给你一个下标从 0 开始的非负整数数组 nums 和两个整数 l 和 r 。
//
//请你返回 nums 中子多重集合的和在闭区间 [l, r] 之间的 子多重集合的数目 。
//
//由于答案可能很大，请你将答案对 109 + 7 取余后返回。
//
//子多重集合 指的是从数组中选出一些元素构成的 无序 集合，每个元素 x 出现的次数可以是 0, 1, ..., occ[x] 次，其中 occ[x] 是元素 x 在数组中的出现次数。
//
//注意：
//
//如果两个子多重集合中的元素排序后一模一样，那么它们两个是相同的 子多重集合 。
//空 集合的和是 0 。
//
//
//示例 1：
//
//输入：nums = [1,2,2,3], l = 6, r = 6
//输出：1
//解释：唯一和为 6 的子集合是 {1, 2, 3} 。
//示例 2：
//
//输入：nums = [2,1,4,2,7], l = 1, r = 5
//输出：7
//解释：和在闭区间 [1, 5] 之间的子多重集合为 {1} ，{2} ，{4} ，{2, 2} ，{1, 2} ，{1, 4} 和 {1, 2, 2} 。
//示例 3：
//
//输入：nums = [1,2,1,3,5,2], l = 3, r = 5
//输出：9
//解释：和在闭区间 [3, 5] 之间的子多重集合为 {3} ，{5} ，{1, 2} ，{1, 3} ，{2, 2} ，{2, 3} ，{1, 1, 2} ，{1, 1, 3} 和 {1, 2, 2} 。
//
//
//提示：
//
//1 <= nums.length <= 2 * 104
//0 <= nums[i] <= 2 * 104
//nums 的和不超过 2 * 104 。
//0 <= l <= r <= 2 * 104

// 重复集合的问题没有解决
func countSubMultisets(nums []int, l int, r int) int {
	mod := 1_000_000_007
	n := len(nums)
	dp := make([]int, r+1)
	dp[0] = 1
	for i := 1; i <= n; i++ {
		tmp := make([]int, r+1)
		for j := 0; j <= r; j++ {
			tmp[j] = dp[j]
			if nums[i-1] <= j {
				tmp[j] = (tmp[j] + dp[j-nums[i-1]] + 1) % mod
			}
		}
		dp = tmp
	}
	if l > 0 {
		return (dp[r] - dp[l-1] + mod) % mod
	} else {
		return dp[r]
	}
}

// 解决重复集合的问题，但是超时了
func countSubMultisets2(nums []int, l int, r int) int {
	mod := 1_000_000_007
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	arr, cnt := make([]int, len(mp)), make([]int, len(mp))
	idx := 0
	for num, c := range mp {
		arr[idx] = num
		cnt[idx] = c
		idx++
	}
	// f(i,j)为前i种数字中的和<=j的数量
	// f(i,j)=f(i-1,j)+f(i-1,j-arr[i-1])+...+f(i-1,j-k*arr[i-1])
	// 其中k为i类数字出现的数量
	n := len(mp)
	dp := make([]int, r+1)
	for i := 0; i <= r; i++ {
		dp[i] = 1
	}
	for i := 1; i <= n; i++ {
		tmp := make([]int, r+1)
		for j := 0; j <= r; j++ {
			tmp[j] = dp[j]
			for k := 1; k <= cnt[i-1] && arr[i-1]*k <= j; k++ {
				tmp[j] = (tmp[j] + dp[j-k*arr[i-1]]) % mod
			}
		}
		dp = tmp
	}
	if l > 0 {
		return (dp[r] - dp[l-1] + mod) % mod
	} else {
		return dp[r]
	}
}

// 增加滑动窗口
func countSubMultisets3(nums []int, l int, r int) int {
	mod := 1_000_000_007
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	arr, cnt := make([]int, len(mp)), make([]int, len(mp))
	idx := 0
	for num, c := range mp {
		arr[idx] = num
		cnt[idx] = c
		idx++
	}
	// f(i,j)为前i种数字中的和<=j的数量
	// f(i,j)=f(i-1,j)+f(i-1,j-arr[i-1])+...+f(i-1,j-k*arr[i-1])
	// 其中k为i类数字出现的数量
	n := len(mp)
	dp := make([]int, r+1)
	for i := 0; i <= r; i++ {
		dp[i] = 1
	}
	for i := 1; i <= n; i++ {
		tmp := make([]int, r+1)
		for j := 0; j <= r; j++ {
			if arr[i-1] == 0 {
				tmp[j] = (cnt[i-1] + 1) * dp[j]
			} else {
				tmp[j] = dp[j]
				if j >= arr[i-1] {
					tmp[j] = (tmp[j] + dp[j-arr[i-1]]) % mod
					if j >= (cnt[i-1]+1)*arr[i-1] {
						tmp[j] = (tmp[j] + mod - dp[j-(cnt[i-1]+1)*arr[i-1]]) % mod
					}
				}
			}
		}
		dp = tmp
	}
	if l > 0 {
		return (dp[r] - dp[l-1] + mod) % mod
	} else {
		return dp[r]
	}
}
