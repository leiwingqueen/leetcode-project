package wc314

func findArray(pref []int) []int {
	n := len(pref)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		if i == 0 {
			res[i] = pref[i]
		} else {
			res[i] = pref[i] ^ pref[i-1]
		}
	}
	return res
}
