package wc354

import "strings"

func longestValidSubstring(word string, forbidden []string) int {
	n := len(word)
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			s := word[i : j+1]
			flag := false
			for _, f := range forbidden {
				if strings.Contains(s, f) {
					flag = true
					break
				}
			}
			if flag && j-i+1 > res {
				res = j - i + 1
			}
		}
	}
	return res
}

func longestValidSubstring2(word string, forbidden []string) int {
	n := len(word)
	check := func(k int) bool {
		if k == 0 {
			return true
		}
		for i := 0; i <= n-k; i++ {
			s := word[i : i+k]
			flag := true
			for _, f := range forbidden {
				if strings.Contains(s, f) {
					flag = false
					break
				}
			}
			if flag {
				return true
			}
		}
		return false
	}
	l, r := 0, n
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

func longestValidSubstring3(word string, forbidden []string) int {
	n := len(word)
	check := func(k int) bool {
		if k == 0 {
			return true
		}
		for i := 0; i <= n-k; i++ {
			s := word[i : i+k]
			flag := true
			for _, f := range forbidden {
				if strings.Contains(s, f) {
					flag = false
					break
				}
			}
			if flag {
				return true
			}
		}
		return false
	}
	l, r := 0, n
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
