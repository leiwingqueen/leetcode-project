package util

func Min(a int, b int) int {
	if a > b {
		return b
	} else {
		return a
	}
}

func Max(a int, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func Abs(a int) int {
	if a > 0 {
		return a
	} else {
		return -a
	}
}
