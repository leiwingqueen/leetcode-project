package math

//给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
//
// 返回 n 的长度。如果不存在这样的 n ，就返回-1。
//
// 注意： n 不符合 64 位带符号整数。
//
//
//
// 示例 1：
//
//
//输入：k = 1
//输出：1
//解释：最小的答案是 n = 1，其长度为 1。
//
// 示例 2：
//
//
//输入：k = 2
//输出：-1
//解释：不存在可被 2 整除的正整数 n 。
//
// 示例 3：
//
//
//输入：k = 3
//输出：3
//解释：最小的答案是 n = 111，其长度为 3。
//
//
//
// 提示：
//
//
// 1 <= k <= 10⁵
//
//
// Related Topics 哈希表 数学 👍 121 👎 0

func smallestRepunitDivByK(k int) int {
	var num int64
	num = 1
	for i := 1; i <= 19; i++ {
		if num%int64(k) == 0 {
			return i
		}
		num = num*10 + 1
	}
	return -1
}

func smallestRepunitDivByK2(k int) int {
	mp := make([]bool, k)
	num := 1
	i := 1
	for {
		if num%k == 0 {
			return i
		}
		if mp[num%k] {
			// 存在环
			return -1
		}
		mp[num%k] = true
		num = num%k*10 + 1
		i++
	}
}
