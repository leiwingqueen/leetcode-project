package bwc128

func scoreOfString(s string) int {
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	res := 0
	for i := 0; i < len(s)-1; i++ {
		res += abs(int(s[i+1]) - int(s[i]))
	}
	return res
}
