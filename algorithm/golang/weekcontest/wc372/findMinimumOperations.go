package wc372

func findMinimumOperations(s1 string, s2 string, s3 string) int {
	p := 0
	for p < len(s1) && p < len(s2) && p < len(s3) && s1[p] == s2[p] && s2[p] == s3[p] {
		p++
	}
	if p == 0 {
		return -1
	} else {
		return len(s1) - p + len(s2) - p + len(s3) - p
	}
}
