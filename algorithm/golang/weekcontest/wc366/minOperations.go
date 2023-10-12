package wc366

import "math"

// 贪心，然而并不是最优解
func minOperations(s1 string, s2 string, x int) int {
	n := len(s1)
	p1, p2 := 0, 0
	res := 0
	for p2 < n {
		for p1 < n && s1[p1] == s2[p1] {
			p1++
		}
		if p1 == n {
			return res
		}
		p2 = p1 + 1
		for p2 < n && s1[p2] == s2[p2] {
			p2++
		}
		if p2 == n {
			return -1
		}
		if p2-p1 < x {
			res += p2 - p1
		} else {
			res += x
		}
		p1 = p2 + 1
	}
	return res
}

func minOperations2(s1 string, s2 string, x int) int {
	n := len(s1)
	oneCnt1, oneCnt2 := 0, 0
	for i := 0; i < n; i++ {
		if s1[i] == '1' {
			oneCnt1++
		}
		if s2[i] == '1' {
			oneCnt2++
		}
	}
	if oneCnt1%2 != oneCnt2%2 {
		return -1
	}
	var dfs func(i, j int, reverse bool) int
	dfs = func(i, j int, reverse bool) int {
		b1, b2 := s1[1]-'0', s2[i]-'0'
		if reverse {
			b1 ^= 1
		}
		if i == 0 {
			if (b1 == b2 && j == 0) || (b1 != b2 && j == 1) {
				return 0
			} else {
				return math.MaxInt32
			}
		}
		if b1 == b2 {
			return dfs(i-1, j, false)
		}
		res := math.MaxInt32
		if j > 0 {
			// 方案1
			sub := dfs(i-1, j-1, false)
			if sub < res {
				res = sub
			}
		}
		sub1 := dfs(i-1, j+1, false) + x
		if sub1 < res {
			res = sub1
		}
		sub2 := dfs(i-1, j, true) + 1
		if sub2 < res {
			res = sub2
		}
		return res
	}
	return dfs(n-1, 0, false)
}
