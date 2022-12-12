package prefixSum

import "math"

func beautySum(s string) int {
	n := len(s)
	prefix := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		prefix[i] = make([]int, 26)
	}
	for i := 1; i <= n; i++ {
		for j := 0; j < 26; j++ {
			prefix[i][j] = prefix[i-1][j]
		}
		prefix[i][int(s[i-1]-'a')]++
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			cnt := make([]int, 26)
			for k := 0; k < 26; k++ {
				cnt[k] = prefix[j+1][k]
			}
			for k := 0; k < 26; k++ {
				cnt[k] -= prefix[i][k]
			}
			mx := 0
			min := math.MaxInt32
			for k := 0; k < 26; k++ {
				if cnt[k] > mx {
					mx = cnt[k]
				}
				if cnt[k] > 0 && cnt[k] < min {
					min = cnt[k]
				}
			}
			res += mx - min
		}
	}
	return res
}
