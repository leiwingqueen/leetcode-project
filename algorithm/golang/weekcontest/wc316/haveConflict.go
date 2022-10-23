package wc316

import (
	"strconv"
	"strings"
)

func haveConflict(event1 []string, event2 []string) bool {
	var convert func(event string) int
	convert = func(event string) int {
		s := strings.Split(event, ":")
		num1, _ := strconv.Atoi(s[0])
		num2, _ := strconv.Atoi(s[1])
		res := num1 * 60
		res += num2
		return res
	}
	t1 := convert(event1[0])
	t2 := convert(event1[1])
	t3 := convert(event2[0])
	t4 := convert(event2[1])
	return !(t2 < t3 || t4 < t1)
}
