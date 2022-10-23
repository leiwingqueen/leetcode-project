package bwc89

import "strings"

func countTime(time string) int {
	s := strings.Split(time, ":")
	s1 := s[0]
	s2 := s[1]
	r1 := 0
	if s1[0] == '?' {
		for i := 0; i <= 2; i++ {
			if s1[1] == '?' {
				for j := 0; j <= 9; j++ {
					if i*10+j < 24 {
						r1++
					}
				}
			} else {
				if i*10+int(s1[1]-'0') < 24 {
					r1++
				}
			}
		}
	} else {
		i := int(s1[0] - '0')
		if s1[1] == '?' {
			for j := 0; j <= 9; j++ {
				if i*10+j < 24 {
					r1++
				}
			}
		} else {
			if i*10+int(s1[1]-'0') < 24 {
				r1++
			}
		}
	}
	r2 := 0
	if s2[0] == '?' {
		for i := 0; i <= 5; i++ {
			if s2[1] == '?' {
				for j := 0; j <= 9; j++ {
					if i*10+j < 60 {
						r2++
					}
				}
			} else {
				if i*10+int(s2[1]-'0') < 60 {
					r2++
				}
			}
		}
	} else {
		i := int(s2[0] - '0')
		if s2[1] == '?' {
			for j := 0; j <= 9; j++ {
				if i*10+j < 60 {
					r2++
				}
			}
		} else {
			if i*10+int(s2[1]-'0') < 60 {
				r2++
			}
		}
	}
	return r1 * r2
}
