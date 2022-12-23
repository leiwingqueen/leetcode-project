package array

func finalValueAfterOperations(operations []string) int {
	x := 0
	for _, op := range operations {
		if op == "X++" || op == "++X" {
			x++
		} else {
			x--
		}
	}
	return x
}
