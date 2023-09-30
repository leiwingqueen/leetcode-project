package twopointer

func dismantlingAction(arr string) int {
	n := len(arr)
	l, r := 0, 0
	mp := make(map[byte]bool)
	res := 0
	for r < n {
		for r < n && !mp[arr[r]] {
			mp[arr[r]] = true
			r++
		}
		if r-l > res {
			res = r - l
		}
		if r == n {
			break
		}
		for arr[l] != arr[r] {
			mp[arr[l]] = false
			l++
		}
		mp[arr[l]] = false
		l++
	}
	return res
}
