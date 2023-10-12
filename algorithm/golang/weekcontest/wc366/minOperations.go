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

// dfs 解法
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
		b1, b2 := s1[i]-'0', s2[i]-'0'
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

// 上面基础上加记忆
func minOperations3(s1 string, s2 string, x int) int {
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
	mem := make([][][]int, n)
	for i := 0; i < n; i++ {
		mem[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			mem[i][j] = make([]int, 2)
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			for l := 0; l < 2; l++ {
				mem[i][j][l] = -1
			}
		}
	}
	var dfs func(i, j int, reverse int) int
	dfs = func(i, j int, reverse int) int {
		b1, b2 := s1[i]-'0', s2[i]-'0'
		if reverse == 1 {
			b1 ^= 1
		}
		if i == 0 {
			if (b1 == b2 && j == 0) || (b1 != b2 && j == 1) {
				return 0
			} else {
				return math.MaxInt32
			}
		}
		if mem[i][j][reverse] >= 0 {
			return mem[i][j][reverse]
		}
		res := math.MaxInt32
		defer func() {
			mem[i][j][reverse] = res
		}()
		if b1 == b2 {
			res = dfs(i-1, j, 0)
			return res
		}
		if j > 0 {
			// 方案1
			sub := dfs(i-1, j-1, 0)
			if sub < res {
				res = sub
			}
		}
		sub1 := dfs(i-1, j+1, 0) + x
		if sub1 < res {
			res = sub1
		}
		sub2 := dfs(i-1, j, 1) + 1
		if sub2 < res {
			res = sub2
		}
		return res
	}
	return dfs(n-1, 0, 0)
}
