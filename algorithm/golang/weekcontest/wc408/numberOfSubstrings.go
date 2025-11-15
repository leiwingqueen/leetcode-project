package wc408

import (
	"math"
	"sort"
)

// 给你一个二进制字符串 s。
//
//请你统计并返回其中 1 显著 的 子字符串 的数量。
//
//如果字符串中 1 的数量 大于或等于 0 的数量的 平方，则认为该字符串是一个 1 显著 的字符串 。
//
//
//
//示例 1：
//
//输入：s = "00011"
//
//输出：5
//
//解释：
//
//1 显著的子字符串如下表所示。
//
//i	j	s[i..j]	0 的数量	1 的数量
//3	3	1	0	1
//4	4	1	0	1
//2	3	01	1	1
//3	4	11	0	2
//2	4	011	1	2
//示例 2：
//
//输入：s = "101101"
//
//输出：16
//
//解释：
//
//1 不显著的子字符串如下表所示。
//
//总共有 21 个子字符串，其中 5 个是 1 不显著字符串，因此有 16 个 1 显著子字符串。
//
//i	j	s[i..j]	0 的数量	1 的数量
//1	1	0	1	0
//4	4	0	1	0
//1	4	0110	2	2
//0	4	10110	2	3
//1	5	01101	2	3
//
//
//提示：
//
//1 <= s.length <= 4 * 104
//s 仅包含字符 '0' 和 '1'。

// 必然超时
func numberOfSubstrings(s string) int {
	n := len(s)
	prefixSum := make([]int, n+1)
	for i, ch := range s {
		prefixSum[i+1] = prefixSum[i]
		if ch == '1' {
			prefixSum[i+1]++
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			cnt1 := prefixSum[j+1] - prefixSum[i]
			cnt0 := j + 1 - i - cnt1
			if cnt1 >= cnt0*cnt0 {
				res++
			}
		}
	}
	return res
}

func numberOfSubstrings2(s string) int {
	n := len(s)
	// 记录0的位置
	var zeroPos []int
	// 前缀和
	prefixZero := make([]int, n+1)
	for i := 0; i < n; i++ {
		prefixZero[i+1] = prefixZero[i]
		if s[i] == '0' {
			zeroPos = append(zeroPos, i)
			prefixZero[i+1]++
		}
	}
	// 最大的0的数量
	maxZero := int(math.Sqrt(float64(n)))
	res := 0
	for i := 0; i < n; i++ {
		// 没有0的场景
		if s[i] == '1' {
			// 找到>i下标的第一个0
			firstZero := sort.Search(len(zeroPos), func(j int) bool {
				return zeroPos[j] > i
			})
			// 右边界在[i,firstZero)范围都是正确答案
			res += firstZero - i
		}
		// 枚举j个0的场景
		for j := 1; j <= maxZero; j++ {
			// 找到第一个下标k,prefixZero[k]>=expect
			expect := prefixZero[i] + j
			k := sort.Search(n+1, func(i int) bool {
				return prefixZero[i] >= expect
			})
			if k <= n {
				// [i,k)刚好是j个0
				// TODO:

			}
		}
	}
	return res
}
