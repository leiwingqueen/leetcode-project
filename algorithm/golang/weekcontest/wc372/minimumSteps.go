package wc372

func minimumSteps(s string) int64 {
	n := len(s)
	// 最后一个0的位置
	p1 := n - 1
	for p1 >= 0 && s[p1] == '1' {
		p1--
	}
	if p1 <= 0 {
		return 0
	}
	var res int64
	p2 := p1 - 1
	for p2 >= 0 {
		// 最后一个1的位置
		for p2 >= 0 && s[p2] == '0' {
			p2--
		}
		if p2 < 0 {
			return res
		}
		res += int64(p1 - p2)
		p1--
		p2--
	}
	return res
}
