package wc405

func getEncryptedString(s string, k int) string {
	n := len(s)
	res := make([]byte, n)
	for i := 0; i < n; i++ {
		res[i] = s[(i+k)%n]
	}
	return string(res)
}
