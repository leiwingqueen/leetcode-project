package dfs

import (
	"math"
	"strconv"
)

// 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
//
//返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
//
//
//
//示例 1：
//
//输入：digits = ["1","3","5","7"], n = 100
//输出：20
//解释：
//可写出的 20 个数字是：
//1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
//示例 2：
//
//输入：digits = ["1","4","9"], n = 1000000000
//输出：29523
//解释：
//我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
//81 个四位数字，243 个五位数字，729 个六位数字，
//2187 个七位数字，6561 个八位数字和 19683 个九位数字。
//总共，可以使用D中的数字写出 29523 个整数。
//示例 3:
//
//输入：digits = ["7"], n = 8
//输出：1
//
//
//提示：
//
//1 <= digits.length <= 9
//digits[i].length == 1
//digits[i] 是从 '1' 到 '9' 的数
//digits 中的所有值都 不同
//digits 按 非递减顺序 排列
//1 <= n <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/numbers-at-most-n-given-digit-set
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// dfs解法
func atMostNGivenDigitSet(digits []string, n int) int {
	res := 0
	var getSize func(n int) []int
	getSize = func(n int) []int {
		i := 0
		arr := make([]int, 0)
		for n > 0 {
			arr = append(arr, n%10)
			i++
			n /= 10
		}
		return arr
	}
	arr := getSize(n)
	for i := 1; i < len(arr); i++ {
		res += int(math.Pow(float64(len(digits)), float64(i)))
	}
	var dfs func(idx int, size int, eq bool) int
	dfs = func(idx int, size int, eq bool) int {
		if idx == size {
			return 1
		}
		s1 := dfs(idx+1, size, true)
		s2 := dfs(idx+1, size, false)
		if !eq {
			return len(digits) * s2
		} else {
			r := 0
			for _, s := range digits {
				num, _ := strconv.Atoi(s)
				if num == arr[size-idx-1] {
					r += s1
				} else if num < arr[size-idx-1] {
					r += s2
				} else {
					break
				}
			}
			return r
		}
	}
	res += dfs(0, len(arr), true)
	return res
}

// dp解法
func atMostNGivenDigitSet2(digits []string, n int) int {
	res := 0
	var getSize func(n int) []int
	getSize = func(n int) []int {
		i := 0
		arr := make([]int, 0)
		for n > 0 {
			arr = append(arr, n%10)
			i++
			n /= 10
		}
		return arr
	}
	arr := getSize(n)
	size := len(arr)
	dp0 := make([]int, size)
	dp0[0] = len(digits)
	for i := 1; i < size; i++ {
		dp0[i] = dp0[i-1] * len(digits)
	}
	for i := 0; i < size-1; i++ {
		res += dp0[i]
	}
	dp1 := make([]int, size)
	for _, s := range digits {
		num, _ := strconv.Atoi(s)
		if num <= arr[0] {
			dp1[0]++
		} else {
			break
		}
	}
	// TODO: 还没想好
	var dfs func(idx int, size int, eq bool) int
	dfs = func(idx int, size int, eq bool) int {
		if idx == size {
			return 1
		}
		s1 := dfs(idx+1, size, true)
		s2 := dfs(idx+1, size, false)
		if !eq {
			return len(digits) * s2
		} else {
			r := 0
			for _, s := range digits {
				num, _ := strconv.Atoi(s)
				if num == arr[size-idx-1] {
					r += s1
				} else if num < arr[size-idx-1] {
					r += s2
				} else {
					break
				}
			}
			return r
		}
	}
	res += dfs(0, len(arr), true)
	return res
}
