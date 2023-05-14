package bwc104

import "strconv"

func countSeniors(details []string) int {
	getAge := func(detail string) int {
		num, _ := strconv.Atoi(detail[11:13])
		return num
	}
	res := 0
	for _, detail := range details {
		if getAge(detail) > 60 {
			res++
		}
	}
	return res
}
