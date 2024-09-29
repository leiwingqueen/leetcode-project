package wc417

// 最笨的方法，穷举
func countOfSubstrings(word string, k int) int {
	mp := map[byte]bool{
		'a': true,
		'e': true,
		'i': true,
		'o': true,
		'u': true,
	}
	n := len(word)
	prefix := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		prefix[i] = make([]int, 26)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 26; j++ {
			prefix[i+1][j] = prefix[i][j]
		}
		prefix[i+1][word[i]-'a']++
	}
	check := func(arr []int) bool {
		c := 0
		for i := 0; i < 26; i++ {
			if mp[byte(i+'a')] {
				if arr[i] == 0 {
					return false
				}
			} else {
				c += arr[i]
			}
		}
		return c == k
	}
	res := 0
	tmp := make([]int, 26)
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			for l := 0; l < 26; l++ {
				tmp[l] = prefix[j+1][l] - prefix[i][l]
			}
			if check(tmp) {
				res++
			}
		}
	}
	return res
}

// 居然超时了？
func countOfSubstrings2(word string, k int) int64 {
	n := len(word)
	mp := map[byte]bool{
		'a': true,
		'e': true,
		'i': true,
		'o': true,
		'u': true,
	}
	check := func(arr []int, k int) bool {
		c := 0
		for i := 0; i < 26; i++ {
			if mp[byte(i+'a')] {
				if arr[i] == 0 {
					return false
				}
			} else {
				c += arr[i]
			}
		}
		return c >= k
	}
	cal := func(k int) int64 {
		var res int64
		l, r := 0, 0
		arr := make([]int, 26)
		for l < n {
			if check(arr, k) {
				res += int64(n - r + 1)
				arr[word[l]-'a']--
				l++
			} else {
				if r >= n {
					break
				}
				arr[word[r]-'a']++
				r++
			}
		}
		return res
	}
	return cal(k) - cal(k+1)
}

// 擦，还差一点
func countOfSubstrings3(word string, k int) int64 {
	n := len(word)
	mp := map[byte]bool{
		'a': true,
		'e': true,
		'i': true,
		'o': true,
		'u': true,
	}
	check := func(cnt1 map[byte]int, cnt2 int, k int) bool {
		for ch := range mp {
			if cnt1[ch] == 0 {
				return false
			}
		}
		return cnt2 >= k
	}
	cal := func(k int) int64 {
		var res int64
		l, r := 0, 0
		cnt1 := make(map[byte]int)
		cnt2 := 0
		for l < n {
			if check(cnt1, cnt2, k) {
				res += int64(n - r + 1)
				if mp[word[l]] {
					cnt1[word[l]]--
				} else {
					cnt2--
				}
				l++
			} else {
				if r >= n {
					break
				}
				if mp[word[r]] {
					cnt1[word[r]]++
				} else {
					cnt2++
				}
				r++
			}
		}
		return res
	}
	return cal(k) - cal(k+1)
}

// 总算过了，极限优化
func countOfSubstrings4(word string, k int) int64 {
	n := len(word)
	mp := map[byte]bool{
		'a': true,
		'e': true,
		'i': true,
		'o': true,
		'u': true,
	}
	cal := func(k int) int64 {
		var res int64
		l, r := 0, 0
		cnt1 := make(map[byte]int)
		cnt2 := 0
		cnt3 := 0
		for l < n {
			if cnt3 == 5 && cnt2 >= k {
				res += int64(n - r + 1)
				if mp[word[l]] {
					cnt1[word[l]]--
					if cnt1[word[l]] == 0 {
						cnt3--
					}
				} else {
					cnt2--
				}
				l++
			} else {
				if r >= n {
					break
				}
				if mp[word[r]] {
					cnt1[word[r]]++
					if cnt1[word[r]] == 1 {
						cnt3++
					}
				} else {
					cnt2++
				}
				r++
			}
		}
		return res
	}
	return cal(k) - cal(k+1)
}
