package bwc87

import (
	"leetcode-go/util"
	"strconv"
	"strings"
)

func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
	days := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	preSum := make([]int, 13)
	for i := 0; i < 12; i++ {
		preSum[i+1] = preSum[i] + days[i]
	}
	var convert func(s string) int
	convert = func(s string) int {
		split := strings.Split(s, "-")
		month, _ := strconv.Atoi(split[0])
		day, _ := strconv.Atoi(split[1])
		d := 0
		d += preSum[month-1]
		d += day
		return d
	}
	t1 := convert(arriveAlice)
	t2 := convert(leaveAlice)
	t3 := convert(arriveBob)
	t4 := convert(leaveBob)
	if t2 < t3 || t4 < t1 {
		return 0
	}
	return util.Min(t2, t4) - util.Max(t1, t3) + 1
}
