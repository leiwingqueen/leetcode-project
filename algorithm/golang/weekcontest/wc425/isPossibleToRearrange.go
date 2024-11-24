package wc425

func isPossibleToRearrange(s string, t string, k int) bool {
	mp := make(map[string]int)
	arr := []byte(s)
	arr2 := []byte(t)
	n := len(s)
	size := n / k
	for i := 0; i < n; i += size {
		mp[string(arr[i:i+size])]++
	}
	for i := 0; i < n; i += size {
		if mp[string(arr2[i:i+size])] <= 0 {
			return false
		}
		mp[string(arr2[i:i+size])]--
	}
	return true
}
