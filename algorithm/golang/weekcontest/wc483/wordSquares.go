package wc483

import "sort"

// 数据量很小，直接枚举即可
// top[0] == left[0], top[3] == right[0]
// bottom[0] == left[3], bottom[3] == right[3]
func wordSquares(words []string) [][]string {
	n := len(words)
	var res [][]string
	var dfs func(path []int, idx int, used []bool)
	dfs = func(path []int, idx int, used []bool) {
		if idx == 4 {
			top, left, right, bottom := words[path[0]], words[path[1]], words[path[2]], words[path[3]]
			if top[0] == left[0] && top[3] == right[0] && bottom[0] == left[3] && bottom[3] == right[3] {
				res = append(res, []string{top, left, right, bottom})
			}
			return
		}
		for i := 0; i < n; i++ {
			if !used[i] {
				used[i] = true
				path[idx] = i
				dfs(path, idx+1, used)
				used[i] = false
			}
		}
	}
	dfs(make([]int, n), 0, make([]bool, n))
	sort.Slice(res, func(i, j int) bool {
		for k := 0; k < 4; k++ {
			if res[i][k] != res[j][k] {
				return res[i][k] < res[j][k]
			}
		}
		// 实际上不会到这里
		return false
	})
	return res
}
