package bwc92

func countPalindromes(s string) int {
	var dfs func(path []byte, idx int, cnt int)
	res := 0
	dfs = func(path []byte, idx int, cnt int) {
		if cnt == 5 {
			flag := true
			for i := 0; i < 2; i++ {
				if path[i] != path[4-i] {
					flag = false
				}
			}
			if flag {
				res++
			}
			return
		}
		if idx == len(s) {
			return
		}
		if cnt <= 2 {
			path[cnt] = s[idx]
			dfs(path, idx+1, cnt+1)
		} else {
			if s[idx] == path[4-cnt] {
				path[cnt] = s[idx]
				dfs(path, idx+1, cnt+1)
			}
		}
		dfs(path, idx+1, cnt)
	}
	dfs(make([]byte, 5), 0, 0)
	return res
}

func countPalindromes2(s string) int {
	mod := 1_000_000_007
	var dfs5 func(start int, end int) int
	var dfs3 func(start int, end int) int
	dfs5 = func(start int, end int) int {
		if end-start+1 < 5 {
			return 0
		}
		res := 0
		for i := start; i <= end-4; i++ {
			for j := i + 4; j <= end; j++ {
				if s[i] == s[j] {
					res = (res + dfs3(i+1, j-1)) % mod
				}
			}
		}
		return res
	}
	dfs3 = func(start int, end int) int {
		if end-start+1 < 3 {
			return 0
		}
		res := 0
		for i := start; i <= end-2; i++ {
			for j := i + 2; j <= end; j++ {
				if s[i] == s[j] {
					res = (res + j - i - 1) % mod
				}
			}
		}
		return res
	}
	return dfs5(0, len(s)-1)
}

// TODO:脑筋急转弯
