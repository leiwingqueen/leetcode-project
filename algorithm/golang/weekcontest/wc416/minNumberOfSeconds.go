package wc416

import "sort"

// (1+x)*x/2*t[i]
func minNumberOfSeconds(mountainHeight int, workerTimes []int) int64 {
	cal := func(x int, wt int) int64 {
		return int64(1+x) * int64(x) / 2 * int64(wt)
	}
	// 计算t分钟每个工人分别能降低山的高度的最大值
	cal2 := func(t int64, wt int) int {
		return sort.Search(mountainHeight+1, func(x int) bool {
			return cal(x, wt) > t
		}) - 1
	}
	check := func(t int64) bool {
		h := 0
		for _, wt := range workerTimes {
			h += cal2(t, wt)
		}
		return h >= mountainHeight
	}
	sort.Ints(workerTimes)
	mx := int64(1+mountainHeight) * int64(mountainHeight) / 2 * int64(workerTimes[0])
	l, r := int64(0), mx
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
