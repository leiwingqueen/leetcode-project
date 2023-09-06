package bwc112

func canBeEqual(s1 string, s2 string) bool {
	if s1[0] == s2[0] {
		if s1[2] != s2[2] {
			return false
		}
		if s1[1] == s2[1] {
			return s1[3] == s2[3]
		} else {
			return s1[1] == s2[3] && s1[3] == s2[1]
		}
	} else {
		if s1[0] != s2[2] || s1[2] != s2[0] {
			return false
		}
		if s1[1] == s2[1] {
			return s1[3] == s2[3]
		} else {
			return s1[1] == s2[3] && s1[3] == s2[1]
		}
	}
}
