package wc390

// 滑动窗口
func maximumLengthSubstring(s string) int {
	n := len(s)
	l, r := 0, 0
	cnt := make([]int, 26)
	res := 0
	for r < n {
		if cnt[s[r]-'a'] <= 1 {
			cnt[s[r]-'a']++
			r++
		} else {
			if r-l > res {
				res = r - l
			}
			cnt[s[l]-'a']--
			l++
		}
	}
	if r-l > res {
		res = r - l
	}
	return res
}
