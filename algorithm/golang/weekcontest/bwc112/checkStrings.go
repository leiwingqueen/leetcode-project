package bwc112

func checkStrings(s1 string, s2 string) bool {
	n := len(s1)
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for i := 0; i < n; i++ {
		if i%2 == 0 {
			cnt1[s1[i]-'a']++
			cnt1[s2[i]-'a']--
		} else {
			cnt2[s1[i]-'a']++
			cnt2[s2[i]-'a']--
		}
	}
	for i := 0; i < 26; i++ {
		if cnt1[i] != 0 || cnt2[i] != 0 {
			return false
		}
	}
	return true
}
