package bwc84

func taskSchedulerII(tasks []int, space int) int64 {
	last := make(map[int]int64)
	cur := int64(1)
	for _, task := range tasks {
		if last[task] == 0 || cur-last[task] >= int64(space) {
			last[task] = cur
		} else {
			diff := int64(space) - (cur - last[task])
			cur += diff
			last[task] = cur
		}
		cur++
	}
	return cur - 1
}
