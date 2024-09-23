package wc416

// (1+x)*x/2*t[i]
func minNumberOfSeconds(mountainHeight int, workerTimes []int) int64 {
	cal := func(t int) int64 {
		return int64(1+mountainHeight) * int64(mountainHeight) * int64(t)
	}
	// 计算t分钟每个工人分别能降低山的高度的最大值
	cal2:= func(t int64)int {

	}
	check := func(t int) bool {
		for _, := range workerTimes {

		}
	}
	var res int64
	for _, t := range workerTimes {
		res = max(res, cal(t))
	}
	return res
}
