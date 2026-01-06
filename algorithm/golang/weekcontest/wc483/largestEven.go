package wc483

// 其实只需要从地位往前删除1即可
func largestEven(s string) string {
	n := len(s)
	i := n - 1
	for i >= 0 && s[i] == '1' {
		i--
	}
	// [0:i]
	return s[:i+1]
}
