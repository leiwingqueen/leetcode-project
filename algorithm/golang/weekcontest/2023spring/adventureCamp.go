package _023spring

import "strings"

func adventureCamp(expeditions []string) int {
	n := len(expeditions)
	mp := make(map[string]bool)
	for _, p := range strings.Split(expeditions[0], "->") {
		mp[p] = true
	}
	res := -1
	mx := 0
	for i := 1; i < n; i++ {
		cnt := 0
		for _, p := range strings.Split(expeditions[i], "->") {
			if p == "" {
				continue
			}
			if !mp[p] {
				cnt++
				mp[p] = true
			}
		}
		if cnt > mx {
			mx = cnt
			res = i
		}
	}
	return res
}
