package array

func areAlmostEqual(s1 string, s2 string) bool {
	diff := make([]int, 0)
	n := len(s1)
	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			diff = append(diff, i)
			if len(diff) > 2 {
				return false
			}
		}
	}
	if len(diff) == 0 {
		return true
	}
	if len(diff) == 1 {
		return false
	}
	return s1[diff[0]] == s2[diff[1]] && s1[diff[1]] == s2[diff[0]]
}
