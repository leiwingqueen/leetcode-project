package wc431

// 给你一个由 正整数 组成的数组 nums。
//
//如果一个数组 arr 满足 prod(arr) == lcm(arr) * gcd(arr)，则称其为 乘积等价数组 ，其中：
//
//prod(arr) 表示 arr 中所有元素的乘积。
//gcd(arr) 表示 arr 中所有元素的最大公因数 (GCD)。
//lcm(arr) 表示 arr 中所有元素的最小公倍数 (LCM)。
//返回数组 nums 的 最长 乘积等价子数组 的长度。
//
//子数组 是数组中连续的、非空的元素序列。
//
//术语 gcd(a, b) 表示 a 和 b 的 最大公因数 。
//
//术语 lcm(a, b) 表示 a 和 b 的 最小公倍数 。
//
//
//
//示例 1：
//
//输入： nums = [1,2,1,2,1,1,1]
//
//输出： 5
//
//解释：
//
//最长的乘积等价子数组是 [1, 2, 1, 1, 1]，其中 prod([1, 2, 1, 1, 1]) = 2， gcd([1, 2, 1, 1, 1]) = 1，以及 lcm([1, 2, 1, 1, 1]) = 2。
//
//示例 2：
//
//输入： nums = [2,3,4,5,6]
//
//输出： 3
//
//解释：
//
//最长的乘积等价子数组是 [3, 4, 5]。
//
//示例 3：
//
//输入： nums = [1,2,3,1,4,5,1]
//
//输出： 5
//
//
//
//提示：
//
//2 <= nums.length <= 100
//1 <= nums[i] <= 10

// 无脑暴力
func maxLength(nums []int) int {
	n := len(nums)
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	lcm := func(a, b int) int {
		return a / gcd(a, b) * b
	}
	res := 1
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			g := nums[i]
			t := 1
			k := 1
			for l := i; l <= j; l++ {
				g = gcd(nums[l], g)
				t = lcm(t, nums[l])
				k *= nums[l]
			}
			if k == g*t {
				res = max(res, j-i+1)
			}
		}
	}
	return res
}
