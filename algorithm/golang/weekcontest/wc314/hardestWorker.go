package wc314

func hardestWorker(n int, logs [][]int) int {
	t := 0
	id := -1
	for i, log := range logs {
		var time int
		if i == 0 {
			time = log[1]
		} else {
			time = log[1] - logs[i-1][1]
		}
		if time > t || (time == t && log[0] < id) {
			t = time
			id = log[0]
		}
	}
	return id
}
