package bwc93

func maximumValue(strs []string) int {
	convert := func(word string) int {
		num := 0
		for i := 0; i < len(word); i++ {
			if word[i] < '0' || word[i] > '9' {
				return len(word)
			}
			num = num*10 + int(word[i]-'0')
		}
		return num
	}
	res := 0
	for _, word := range strs {
		sub := convert(word)
		if sub > res {
			res = sub
		}
	}
	return res
}
