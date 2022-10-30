package wc317

func mostPopularCreator(creators []string, ids []string, views []int) [][]string {
	n := len(creators)
	mp := make(map[string]int)
	mp2 := make(map[string]int)
	mx := 0
	for i := 0; i < n; i++ {
		mp[creators[i]] += views[i]
		if mp[creators[i]] > mx {
			mx = mp[creators[i]]
		}
		v, exist := mp2[creators[i]]
		if !exist {
			mp2[creators[i]] = i
		} else {
			if views[i] > views[v] {
				mp2[creators[i]] = i
			}
		}
	}
	res := make([][]string, 0)
	for k, v := range mp {
		if v == mx {
			res = append(res, []string{k, ids[mp2[k]]})
		}
	}
	return res
}
