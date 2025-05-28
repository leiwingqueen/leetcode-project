package bwc157

import (
	"math"
	"sort"
	"strconv"
)

// 给定一个字符串 s，找出可以由其 子字符串 组成的 3个最大的不同质数 的和。
//
//返回这些质数的 总和 ，如果少于 3 个不同的质数，则返回 所有 不同质数的和。
//
//质数是大于 1 且只有两个因数的自然数：1和它本身。
//
//子字符串 是字符串中的一个连续字符序列。
//
//注意：每个质数即使出现在 多个 子字符串中，也只能计算 一次 。此外，将子字符串转换为整数时，忽略任何前导零。
//
//
//
//示例 1：
//
//输入： s = "12234"
//
//输出： 1469
//
//解释：
//
//由 "12234" 的子字符串形成的不同质数为 2 ，3 ，23 ，223 和 1223。
//最大的 3 个质数是 1223、223 和 23。它们的和是 1469。
//示例 2：
//
//输入： s = "111"
//
//输出： 11
//
//解释：
//
//由 "111" 的子字符串形成的不同质数是 11。
//由于只有一个质数，所以结果是 11。
//
//
//提示：
//
//1 <= s.length <= 10
//s 仅由数字组成。

// 这个数据量完全可以穷举
func sumOfLargestPrimes(s string) int64 {
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
	n := len(s)
	mp := make(map[int]struct{})
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			num, _ := strconv.Atoi(s[i : j+1])
			if isPrime(num) {
				mp[num] = struct{}{}
			}
		}
	}
	var arr []int
	for k := range mp {
		arr = append(arr, k)
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i] > arr[j]
	})
	var res int64
	for i := 0; i < len(arr) && i < 3; i++ {
		res += int64(arr[i])
	}
	return res
}
