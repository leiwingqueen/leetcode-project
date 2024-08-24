package wc411

// 只要0和1的数量均不大于k，那么可以理解为除非
// 确定右边界，计算满足条件的左边界
func countKConstraintSubstrings(s string, k int) int {
	n := len(s)
	l, r := 0, 0
	cnt0, cnt1 := 0, 0
	res := 0
	for l < n {
		for r < n {
			if s[r] == '1' {
				cnt1++
			} else {
				cnt0++
			}
			if cnt0 > k && cnt1 > k {
				break
			}
			r++
		}
		//[l,r)
		res += r - l
		if s[l] == '1' {
			cnt1--
		} else {
			cnt0--
		}
		l++
	}
	return res
}

func countKConstraintSubstrings2(s string, k int) int {
	n := len(s)
	l, r := 0, 0
	cnt0, cnt1 := 0, 0
	res := 0
	for r < n {
		for cnt0 > k && cnt1 > k {
			if s[l] == '1' {
				cnt1--
			} else {
				cnt0--
			}
			l++
		}
		// 以r为右边界
		// [l,r)
		res += r - l
		if s[r] == '1' {
			cnt1++
		} else {
			cnt0++
		}
		r++
	}
	res += r - l
	return res
}
