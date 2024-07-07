package wc405

// 先试试暴力，超时
// 这里最大的问题是匹配的复杂度
func minimumCost(target string, words []string, costs []int) int {
	n := len(target)
	match := func(src []byte, tar []byte) bool {
		if len(tar) < len(src) {
			return false
		}
		for i, ch := range src {
			if tar[i] != ch {
				return false
			}
		}
		return true
	}
	var dfs func(idx int) int
	dfs = func(idx int) int {
		if idx == n {
			return 0
		}
		res := -1
		for i, word := range words {
			if match([]byte(word), []byte(target)[idx:]) {
				sub := dfs(idx + len(word))
				if sub >= 0 {
					sub += costs[i]
					if res < 0 || sub < res {
						res = sub
					}
				}
			}
		}
		return res
	}
	return dfs(0)
}

// 增加记忆，还剩100个用例没有过
func minimumCost2(target string, words []string, costs []int) int {
	n := len(target)
	mem := make(map[int]int)
	match := func(src []byte, tar []byte) bool {
		if len(tar) < len(src) {
			return false
		}
		for i, ch := range src {
			if tar[i] != ch {
				return false
			}
		}
		return true
	}
	var dfs func(idx int) int
	dfs = func(idx int) int {
		if idx == n {
			return 0
		}
		if v, ok := mem[idx]; ok {
			return v
		}
		res := -1
		for i, word := range words {
			if match([]byte(word), []byte(target)[idx:]) {
				sub := dfs(idx + len(word))
				if sub >= 0 {
					sub += costs[i]
					if res < 0 || sub < res {
						res = sub
					}
				}
			}
		}
		mem[idx] = res
		return res
	}
	return dfs(0)
}
