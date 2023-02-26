package wc334

func divisibilityArray(word string, m int) []int {
	n := len(word)
	res := make([]int, n)
	mod := 0
	for i := 0; i < n; i++ {
		num := int(word[i] - '0')
		s := (mod*10)%m + num
		if s%m == 0 {
			res[i] = 1
		} else {
			res[i] = 0
		}
		mod = s % m
	}
	return res
}
