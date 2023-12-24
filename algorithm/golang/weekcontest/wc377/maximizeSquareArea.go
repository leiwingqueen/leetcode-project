package wc377

import "sort"

func maximizeSquareArea(m int, n int, hFences []int, vFences []int) int {
	hFences = append(hFences, 1)
	hFences = append(hFences, m)
	vFences = append(vFences, 1)
	vFences = append(vFences, n)
	mp := make(map[int]bool)
	for _, v := range hFences {
		mp[v] = true
	}
	mp[1] = true
	mp[m] = true
	sort.Ints(hFences)
	sort.Ints(vFences)
	res := -1
	for _, x := range hFences {
		for j, y := range vFences {
			for i := j + 1; i < len(vFences); i++ {
				k := vFences[i]
				// 左上角[x,y],右上角[x,k]，需要检查是否存在x+k-y这个横轴
				if mp[x+k-y] {
					s := (k - y) * (k - y)
					if s > res {
						res = s
					}
				}
			}
		}
	}
	return res
}
