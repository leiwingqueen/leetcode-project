package bwc111

func canMakeSubsequence(str1 string, str2 string) bool {
	p1, p2 := 0, 0
	for p1 < len(str1) && p2 < len(str2) {
		next := str1[p1] + 1
		if str1[p1] == 'z' {
			next = 'a'
		}
		if str1[p1] == str2[p2] || next == str2[p2] {
			p1++
			p2++
		} else {
			p1++
		}
	}
	return p2 == len(str2)
}
