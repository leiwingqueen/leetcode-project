package wc356

func numberOfEmployeesWhoMetTarget(hours []int, target int) int {
	cnt := 0
	for _, h := range hours {
		if h >= target {
			cnt++
		}
	}
	return cnt
}
