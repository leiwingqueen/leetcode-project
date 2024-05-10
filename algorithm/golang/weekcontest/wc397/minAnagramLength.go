package wc397

func minAnagramLength(s string) int {
	n := len(s)
	preSum := make([][]int, 26)
	for i := 0; i < 26; i++ {
		preSum[i] = make([]int, n+1)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 26; j++ {
			preSum[j][i+1] = preSum[j][i]
		}
		preSum[s[i]-'a'][i+1]++
	}
	check := func(k int) bool {
		if n%k != 0 {
			return false
		}
		cnt := make([]int, 26)
		for i := 0; i < 26; i++ {
			cnt[i] = preSum[i][k]
		}
		for i := k; i < n; i += k {
			// [i,i+k)计算
			for j := 0; j < 26; j++ {
				if preSum[j][i+k]-preSum[j][i] != cnt[j] {
					return false
				}
			}
		}
		return true
	}
	for i := 1; i <= n; i++ {
		if check(i) {
			return i
		}
	}
	return -1
}
