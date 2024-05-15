package wc397

import (
	"math"
)

// 给你一个数组 nums ，它是 [0, 1, 2, ..., n - 1] 的一个
//排列
// 。对于任意一个 [0, 1, 2, ..., n - 1] 的排列 perm ，其 分数 定义为：
//
//score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|
//
//返回具有 最低 分数的排列 perm 。如果存在多个满足题意且分数相等的排列，则返回其中
//字典序最小
//的一个。
//
//
//
//示例 1：
//
//输入：nums = [1,0,2]
//
//输出：[0,1,2]
//
//解释：
//
//
//
//字典序最小且分数最低的排列是 [0,1,2]。这个排列的分数是 |0 - 0| + |1 - 2| + |2 - 1| = 2 。
//
//示例 2：
//
//输入：nums = [0,2,1]
//
//输出：[0,2,1]
//
//解释：
//
//
//
//字典序最小且分数最低的排列是 [0,2,1]。这个排列的分数是 |0 - 1| + |2 - 2| + |1 - 0| = 2 。
//
//
//
//提示：
//
//2 <= n == nums.length <= 14
//nums 是 [0, 1, 2, ..., n - 1] 的一个排列。

// 用14个bit表示前k个选择的集合,假设为s，且最后一个数字为k
// f(s,k)=min{f(s^(1<<k),l)+|p[l]-a[p[k]]|}，其中l!=k，且1<<l&s!=0
func findPermutation(nums []int) []int {
	abs := func(num int) int {
		if num > 0 {
			return num
		} else {
			return -num
		}
	}
	n := len(nums)
	var dfs func(state int, k int, cnt int) int
	dfs = func(state int, k int, cnt int) int {
		if cnt == 1 {
			return 0
		}
		r := math.MaxInt32
		// 第一个数字必然为0，从后面开始选
		for i := 1; i < n; i++ {
			if state&(1<<i) != 0 {
				// 倒数第二个数字
				d := dfs(state^(1<<k), i, cnt-1) + abs(i-nums[k])
				if cnt == n {
					d += abs(k - nums[0])
				}
				r = min(d, r)
			}
		}
		return r
	}
	return nil
}
