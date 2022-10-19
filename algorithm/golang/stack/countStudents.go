package stack

func countStudents(students []int, sandwiches []int) int {
	for _, s := range sandwiches {
		j := 0
		n := len(students)
		for ; j < n; j++ {
			if students[0] == s {
				sandwiches = sandwiches[1:]
				students = students[1:]
				break
			} else {
				students = append(students, students[0])
				students = students[1:]
			}
		}
		if j == n {
			return n
		}
	}
	return 0
}
