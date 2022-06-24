package wc298

import (
	"fmt"
)

//给你一个二进制字符串 s 和一个正整数 k 。
//
//请你返回 s 的 最长 子序列，且该子序列对应的 二进制 数字小于等于 k 。
//
//注意：
//
//子序列可以有 前导 0 。
//空字符串视为 0 。
//子序列 是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。
//
//
//示例 1：
//
//输入：s = "1001010", k = 5
//输出：5
//解释：s 中小于等于 5 的最长子序列是 "00010" ，对应的十进制数字是 2 。
//注意 "00100" 和 "00101" 也是可行的最长子序列，十进制分别对应 4 和 5 。
//最长子序列的长度为 5 ，所以返回 5 。
//示例 2：
//
//输入：s = "00101001", k = 1
//输出：6
//解释："000001" 是 s 中小于等于 1 的最长子序列，对应的十进制数字是 1 。
//最长子序列的长度为 6 ，所以返回 6 。
//
//
//提示：
//
//1 <= s.length <= 1000
//s[i] 要么是 '0' ，要么是 '1' 。
//1 <= k <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func longestSubsequence(s string, k int) int {
	var dfs func(n int, sum int) int
	//即便增加了cache，最后一个用例还是会超时
	mp := make(map[string]int)
	dfs = func(n int, sum int) int {
		if n == 0 {
			return 0
		}
		key := fmt.Sprintf("%d_%d", n, sum)
		if v, exist := mp[key]; exist {
			return v
		}
		//不选
		s1 := dfs(n-1, sum)
		res := s1
		defer func() {
			mp[key] = res
		}()
		if s[n-1] == '0' || sum&0b01 == 1 {
			s2 := dfs(n-1, sum>>1) + 1
			if s2 > s1 {
				res = s2
			}
		} else {
			tmp := sum >> 1
			if tmp == 0 {
				return res
			}
			s2 := dfs(n-1, tmp-1) + 1
			if s2 > s1 {
				res = s2
			}
		}
		return res
	}
	return dfs(len(s), k)
}

//贪心解法
func longestSubsequence2(s string, k int) int {
	cnt := 0
	//先选择全部的0
	for i := 0; i < len(s); i++ {
		if s[i] == '0' {
			cnt++
		}
	}
	num := 0
	bit := 1
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] == '1' {
			num += bit
			if num > k {
				break
			}
			cnt++
		}
		//提前退出，避免数据溢出
		if bit > k {
			break
		}
		bit <<= 1
	}
	return cnt
}
