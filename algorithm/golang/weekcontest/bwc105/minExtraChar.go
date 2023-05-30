package bwc105

import "strings"

// 最傻的回溯，超时
func minExtraChar(s string, dictionary []string) int {
	dictMap := make(map[string]struct{})
	for _, d := range dictionary {
		dictMap[d] = struct{}{}
	}
	var dfs func(str string) int
	dfs = func(str string) int {
		res := len(str)
		for d := range dictMap {
			idx := strings.Index(str, d)
			if idx >= 0 {
				// 分成两部分[0,idx)和[idx+len(d),n)
				sub := 0
				if idx > 0 {
					sub += dfs(str[:idx])
				}
				if idx+len(d) < len(str) {
					sub += dfs(str[idx+len(d):])
				}
				if sub < res {
					res = sub
				}
			}
		}
		return res
	}
	return dfs(s)
}
