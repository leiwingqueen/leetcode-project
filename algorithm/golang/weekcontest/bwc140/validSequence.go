package bwc140

// dfs超时
func validSequence(word1 string, word2 string) []int {
	n1, n2 := len(word1), len(word2)
	var dfs func(idx1, idx2 int, change bool) ([]int, bool)
	dfs = func(idx1, idx2 int, change bool) ([]int, bool) {
		if idx2 == n2 {
			return []int{}, true
		}
		if idx1 == n1 {
			return []int{}, false
		}
		if word1[idx1] == word2[idx2] {
			s, b := dfs(idx1+1, idx2+1, change)
			if b {
				return append([]int{idx1}, s...), true
			} else {
				return []int{}, false
			}
		}
		if !change {
			s, b := dfs(idx1+1, idx2+1, true)
			if b {
				return append([]int{idx1}, s...), true
			}
			return dfs(idx1+1, idx2, false)
		} else {
			return dfs(idx1+1, idx2, true)
		}
	}
	res, _ := dfs(0, 0, false)
	return res
}
