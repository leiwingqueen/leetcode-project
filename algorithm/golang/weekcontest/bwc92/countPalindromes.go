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
func countPalindromes3(s string) int {
	mod := 1_000_000_007
	n := len(s)
	// 检查以xy开头的子序列的数量
	// f(i,x,y)表示[0,i)中的xy子序列的数量
	// 假设s[i-1]!=y,f(i,x,y)=f(i-1,x,y)
	// 假设s[i-1]==y,f(i,x,y)=f(i-1,x,y)+pre(i-1,x)。其中pre表示前i-1个中x的个数
	preSum := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		preSum[i] = make([]int, 10)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 10; j++ {
			preSum[i+1][j] = preSum[i][j]
		}
		preSum[i+1][s[i]-'0']++
	}
	f := make([][][]int, n)
	for i := 0; i < n; i++ {
		f[i] = make([][]int, 10)
		for j := 0; j < 10; j++ {
			f[i][j] = make([]int, 10)
		}
	}
	for i := 1; i < n; i++ {
		for x := 0; x < 10; x++ {
			for y := 0; y < 10; y++ {
				f[i][x][y] = f[i-1][x][y]
				if s[i-1] == byte(y+'0') {
					f[i][x][y] = (f[i][x][y] + preSum[i-1][x]) % mod
				}
			}
		}
	}
	// similar to the right
	preSum2 := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		preSum2[i] = make([]int, 10)
	}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 10; j++ {
			preSum2[n-i][j] = preSum2[n-i-1][j]
		}
		preSum2[n-i][s[i]-'0']++
	}
	g := make([][][]int, n)
	for i := 0; i < n; i++ {
		g[i] = make([][]int, 10)
		for j := 0; j < 10; j++ {
			g[i][j] = make([]int, 10)
		}
	}
	for i := n - 2; i >= 0; i-- {
		for x := 0; x < 10; x++ {
			for y := 0; y < 10; y++ {
				g[i][x][y] = g[i+1][x][y]
				if s[i+1] == byte(x+'0') {
					g[i][x][y] = (g[i][x][y] + preSum2[n-i-2][y]) % mod
				}
			}
		}
	}
	// 计算总和
	res := 0
	for i := 2; i < n-2; i++ {
		for x := 0; x < 10; x++ {
			for y := 0; y < 10; y++ {
				res = (res + (f[i][x][y]*g[i][y][x])%mod) % mod
			}
		}
	}
	return res
}
