package bwc105

import (
	"strings"
)

// 最傻的回溯，勉强通过
func minExtraChar(s string, dictionary []string) int {
	mem := make(map[string]int)
	var dfs func(str string) int
	dfs = func(str string) int {
		if v, exist := mem[str]; exist {
			return v
		}
		res := len(str)
		for _, d := range dictionary {
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
		mem[str] = res
		return res
	}
	return dfs(s)
}

// 上面基础的回溯稍微优化一下，但这里有个问题，不一定每次分割都必须选择第一个匹配的子串
func minExtraChar2(s string, dictionary []string) int {
	var dfs func(str string, i int) int
	dfs = func(str string, i int) int {
		if i == len(dictionary) {
			return len(str)
		}
		idx := strings.Index(str, dictionary[i])
		if idx < 0 {
			return dfs(str, i+1)
		}
		res := 0
		// 分成两部分[0,idx)和[idx+len(d),n)
		if idx > 0 {
			res += dfs(str[:idx], i)
		}
		if idx+len(dictionary[i]) < len(str) {
			res += dfs(str[idx+len(dictionary[i]):], i)
		}
		return res
	}
	return dfs(s, 0)
}
