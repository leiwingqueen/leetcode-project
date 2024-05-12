package wc396

func minimumOperationsToMakeKPeriodic(word string, k int) int {
	n := len(word)
	mp := make(map[string]int)
	for i := 0; i < n; i += k {
		mp[word[i:i+k]]++
	}
	mx := 0
	for _, v := range mp {
		mx = max(mx, v)
	}
	return n/k - mx
}
