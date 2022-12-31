package array

import "sort"

func minMovesToSeat(seats []int, students []int) int {
	sort.Ints(seats)
	sort.Ints(students)
	abs := func(a int) int {
		if a < 0 {
			return -a
		} else {
			return a
		}
	}
	res := 0
	for i, s := range seats {
		res += abs(students[i] - s)
	}
	return res
}
