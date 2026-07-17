package wc510

import (
	"strconv"
	"strings"
)

func secondsBetweenTimes(startTime string, endTime string) int {
	convert := func(t string) int {
		s := strings.Split(t, ":")
		n1, _ := strconv.Atoi(s[0])
		n2, _ := strconv.Atoi(s[1])
		n3, _ := strconv.Atoi(s[2])
		return n1*3600 + n2*60 + n3
	}
	return convert(endTime) - convert(startTime)
}
