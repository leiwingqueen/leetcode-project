package wc455

import "math"

// 给你一个整数数组 nums。
//
//如果数组中任一元素的 频次 是 质数，返回 true；否则，返回 false。
//
//元素 x 的 频次 是它在数组中出现的次数。
//
//质数是一个大于 1 的自然数，并且只有两个因数：1 和它本身。
//
//
//
//示例 1：
//
//输入： nums = [1,2,3,4,5,4]
//
//输出： true
//
//解释：
//
//数字 4 的频次是 2，而 2 是质数。
//
//示例 2：
//
//输入： nums = [1,2,3,4,5]
//
//输出： false
//
//解释：
//
//所有元素的频次都是 1。
//
//示例 3：
//
//输入： nums = [2,2,2,4,4]
//
//输出： true
//
//解释：
//
//数字 2 和 4 的频次都是质数。
//
//
//
//提示：
//
//1 <= nums.length <= 100
//0 <= nums[i] <= 100

func checkPrimeFrequency(nums []int) bool {
	isPrime := func(num int) bool {
		if num <= 1 {
			return false
		}
		sqrt := int(math.Sqrt(float64(num)))
		for i := 2; i <= sqrt; i++ {
			if num%i == 0 {
				return false
			}
		}
		return true
	}
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	for _, v := range mp {
		if isPrime(v) {
			return true
		}
	}
	return false
}
