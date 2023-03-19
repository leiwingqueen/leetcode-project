package dp

// 暴力解法，时间复杂度O(n^2)
func checkPalindromeFormation(a string, b string) bool {
	check := func(s string) bool {
		l, r := 0, len(s)-1
		for l < r {
			if s[l] != s[r] {
				return false
			}
			l++
			r--
		}
		return true
	}
	n := len(a)
	for i := 0; i < n; i++ {
		prefix1, suffix1 := a[:i], a[i:]
		prefix2, suffix2 := b[:i], b[i:]
		if check(prefix1+suffix2) || check(prefix2+suffix1) {
			return true
		}
	}
	return false
}

// 优化解法，双指针
func checkPalindromeFormation2(a string, b string) bool {
	n := len(a)
	l, r := 0, n-1
	for l < r {
		if a[l] != b[r] {
			break
		}
		l++
		r--
	}
	if l >= r {
		return true
	}
	l, r = 0, n-1
	for l < r {
		if b[l] != a[r] {
			break
		}
		l++
		r--
	}
	return l >= r
}
