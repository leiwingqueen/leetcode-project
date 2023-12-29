package wc377

import (
	"sort"
)

// 超时，假设hFences的长度为a,vFences的长度为b，那么
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
			for i := len(vFences) - 1; i >= j+1; i-- {
				k := vFences[i]
				// 左上角[x,y],右上角[x,k]，需要检查是否存在x+k-y这个横轴
				if mp[x+k-y] {
					s := (k - y) * (k - y)
					if s > res {
						res = s
					}
					break
				}
			}
		}
	}
	return res
}

func maximizeSquareArea2(m int, n int, hFences []int, vFences []int) int {
	hFences = append(hFences, []int{1, m}...)
	vFences = append(vFences, []int{1, n}...)
	sort.Ints(hFences)
	sort.Ints(vFences)
	mp := make(map[int]bool)
	for _, v := range hFences {
		mp[v] = true
	}
	v := len(vFences)
	res := 0
	// 斜线起点为[1,c]，查找最左和最右的点
	for i, c := range vFences {
		l, r := -1, -1
		for j := i; j < v; j++ {
			if mp[vFences[j]-c+1] {
				if l < 0 {
					l = vFences[j]
				}
				r = vFences[j]
			}
		}
		if l >= 0 && r >= 0 && r-l > res {
			res = r - l
		}
	}
	// 起点在[c,1]
	for i, c := range hFences {
		l, r := -1, -1
		for j := i; j < v; j++ {
			if mp[c+vFences[j]-1] {
				if l < 0 {
					l = vFences[j]
				}
				r = vFences[j]
			}
		}
		if l >= 0 && r >= 0 && r-l > res {
			res = r - l
		}
	}
	if res == 0 {
		return -1
	}
	mod := 1_000_000_007
	return ((res % mod) * (res % mod)) % mod
}

func maximizeSquareArea3(m int, n int, hFences []int, vFences []int) int {
	cal := func(arr []int, mx int) map[int]bool {
		arr = append(arr, []int{1, mx}...)
		sort.Ints(arr)
		mp := make(map[int]bool)
		k := len(arr)
		for i := 0; i < k; i++ {
			for j := i + 1; j < k; j++ {
				mp[arr[j]-arr[i]] = true
			}
		}
		return mp
	}
	m1 := cal(hFences, m)
	m2 := cal(vFences, n)
	res := 0
	for k1 := range m1 {
		if m2[k1] && k1 > res {
			res = k1
		}
	}
	if res == 0 {
		return -1
	} else {
		mod := 1_000_000_007
		return ((res % mod) * (res % mod)) % mod
	}
}
