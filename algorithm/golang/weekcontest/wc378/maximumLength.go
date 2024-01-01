package wc378

// 简单解法
func maximumLength(s string) int {
	n := len(s)
	check := func(mx int) bool {
		// 每个字符满足条件的最大长度
		cnt := make([]int, 26)
		for i, ch := range s {
			j := i
			for j < i+mx && j < n && s[j] == s[i] {
				j++
			}
			if j == i+mx {
				cnt[ch-'a']++
				if cnt[ch-'a'] >= 3 {
					return true
				}
			}
		}
		return false
	}
	if !check(1) {
		return -1
	}
	l, r := 1, n
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}

func maximumLength2(s string) int {
	// 双指针记录每个节点开始的最长的特殊字符串
	n := len(s)
	arr := make([]int, n)
	l, r := 0, 0
	for l < n {
		for l < n && r < n && s[r] == s[l] {
			r++
		}
		arr[l] = r - l
		l++
	}
	check := func(mx int) bool {
		cnt := make([]int, 26)
		for i, ch := range s {
			if arr[i] >= mx {
				cnt[ch-'a']++
				if cnt[ch-'a'] >= 3 {
					return true
				}
			}
		}
		return false
	}
	if !check(1) {
		return -1
	}
	l, r = 1, n
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
