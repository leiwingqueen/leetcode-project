package wc361

func minimumOperations(num string) int {
	n := len(num)
	find := func(n1, n2 int) int {
		p := n - 1
		cnt := 0
		// 找到第一个0
		for p >= 0 && int(num[p]-'0') != n1 {
			p--
		}
		cnt += n - p - 1
		if p <= 0 {
			return 0
		}
		// 找到第一个5或者0
		p2 := p - 1
		for p2 >= 0 && int(num[p2]-'0') != n2 {
			p2--
		}
		if p2 < 0 {
			return len(num)
		} else {
			cnt += p - 1 - p2
			return cnt
		}
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	res := n
	for i := 0; i < n; i++ {
		if num[i] == '0' {
			res = n - 1
			break
		}
	}
	res = min(res, find(0, 0))
	res = min(res, find(0, 5))
	res = min(res, find(5, 2))
	res = min(res, find(5, 7))
	return res
}
