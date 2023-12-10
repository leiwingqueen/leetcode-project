package wc375

func countTestedDevices(batteryPercentages []int) int {
	n := len(batteryPercentages)
	cnt := 0
	for i := 0; i < n; i++ {
		b := batteryPercentages[i]
		if b > 0 {
			cnt++
			for j := i + 1; j < n; j++ {
				batteryPercentages[j]--
			}
		}
	}
	return cnt
}
