package greedy

// You are given positive integers n and target.
//
//An array nums is beautiful if it meets the following conditions:
//
//nums.length == n.
//nums consists of pairwise distinct positive integers.
//There doesn't exist two distinct indices, i and j, in the range [0, n - 1], such that nums[i] + nums[j] == target.
//Return the minimum possible sum that a beautiful array could have modulo 109 + 7.
//
//
//
//Example 1:
//
//Input: n = 2, target = 3
//Output: 4
//Explanation: We can see that nums = [1,3] is beautiful.
//- The array nums has length n = 2.
//- The array nums consists of pairwise distinct positive integers.
//- There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
//It can be proven that 4 is the minimum possible sum that a beautiful array could have.
//Example 2:
//
//Input: n = 3, target = 3
//Output: 8
//Explanation: We can see that nums = [1,3,4] is beautiful.
//- The array nums has length n = 3.
//- The array nums consists of pairwise distinct positive integers.
//- There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
//It can be proven that 8 is the minimum possible sum that a beautiful array could have.
//Example 3:
//
//Input: n = 1, target = 1
//Output: 1
//Explanation: We can see, that nums = [1] is beautiful.
//
//
//Constraints:
//
//1 <= n <= 109
//1 <= target <= 109

// 超时
func minimumPossibleSum(n int, target int) int {
	mod := 1_000_000_007
	p := 1
	mp := make(map[int]bool)
	sum := 0
	for i := 0; i < n; i++ {
		for mp[target-p] {
			p++
		}
		mp[p] = true
		sum = (sum + p) % mod
		p++
	}
	return sum
}

// 分场景
// [1,target-2]的部分，一共target-2个数字
// target-1这个数字需要跳过

// 1匹配target-1,2匹配target-2,3匹配target-3，直到target/2,(target+1)/2
// 所以前面只需要保留[1,target/2]，所以这足够n个数字，就直接返回结果
// 否则就从target往后取
func minimumPossibleSum2(n int, target int) int {
	mod := 1_000_000_007
	if target/2 >= n {
		res := int64(1+n) * int64(n) / 2
		return int(res % int64(mod))
	}
	k := target / 2
	sum := int((int64(1+k) * int64(k) / 2) % int64(mod))
	n -= k
	// 计算[target,target+n-1]
	t := int((int64(target+target+n-1) * int64(n) / 2) % int64(mod))
	sum = (sum + t) % mod
	return sum
}
