package wc347

func removeTrailingZeros(num string) string {
	n := len(num)
	i := n - 1
	for i >= 0 && num[i] == '0' {
		i--
	}
	if i < 0 {
		return "0"
	}
	return num[:i+1]
}
