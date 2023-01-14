package math

// 给你一个由正整数组成的数组 nums 。
//
//数字序列的 最大公约数 定义为序列中所有整数的共有约数中的最大整数。
//
//例如，序列 [4,6,16] 的最大公约数是 2 。
//数组的一个 子序列 本质是一个序列，可以通过删除数组中的某些元素（或者不删除）得到。
//
//例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
//计算并返回 nums 的所有 非空 子序列中 不同 最大公约数的 数目 。
//
//
//
//示例 1：
//
//
//输入：nums = [6,10,3]
//输出：5
//解释：上图显示了所有的非空子序列与各自的最大公约数。
//不同的最大公约数为 6 、10 、3 、2 和 1 。
//示例 2：
//
//输入：nums = [5,15,40,5,6]
//输出：7
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 2 * 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-different-subsequences-gcds
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 还是得看官解
func countDifferentSubsequenceGCDs(nums []int) int {
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	mx := 0
	for _, num := range nums {
		if num > mx {
			mx = num
		}
	}
	occur := make([]bool, mx+1)
	for _, num := range nums {
		occur[num] = true
	}
	// 检查该最大公约数是否存在，只需要检查num的倍数
	// k1*num,k2*num,...,kn*num，假设gcd(k1,k2,...,kn)为1，则最大公约数为num
	check := func(num int) bool {
		subGcd := 0
		for i := 1; i*num <= mx; i++ {
			if occur[i*num] {
				if subGcd == 0 {
					subGcd = i
				} else {
					subGcd = gcd(subGcd, i)
				}
			}
			if subGcd == 1 {
				return true
			}
		}
		return false
	}
	// 遍历所有的可能的gcd
	cnt := 0
	for i := 1; i <= mx; i++ {
		if check(i) {
			cnt++
		}
	}
	return cnt
}
