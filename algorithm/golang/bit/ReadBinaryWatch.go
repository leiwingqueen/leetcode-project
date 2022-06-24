package bit

import "fmt"

func readBinaryWatch(turnedOn int) []string {
	res := make([]string, 0, 720)
	for hour := 0; hour < 12; hour++ {
		for minute := 0; minute < 60; minute++ {
			if bitCount(hour)+bitCount(minute) == turnedOn {
				str := fmt.Sprintf("%d:%02d", hour, minute)
				fmt.Printf("%s\n", str)
				res = append(res, str)
			}
		}
	}
	return res
}

func bitCount(num int) int {
	cnt := 0
	for num > 0 {
		cnt += num & 0b1
		num >>= 1
	}
	return cnt
}
