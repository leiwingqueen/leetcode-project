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
