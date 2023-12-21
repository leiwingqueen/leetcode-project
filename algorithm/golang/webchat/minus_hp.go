package webchat

func minus_hp(s1, s2 string) string {
	p1, p2 := len(s1)-1, len(s2)-1
	var res []byte
	minus := 0
	for p1 >= 0 || p2 >= 0 {
		num1 := 0
		if p1 >= 0 {
			num1 = int(s1[p1] - '0')
		}
		num2 := 0
		if p2 >= 0 {
			num2 = int(s2[p2] - '0')
		}
		if minus > 0 {
			if num1 >= 0 {
				num1--
				minus--
			} else {
				// 继续向上借位
			}
		}
		if num1 >= num2 {
			res = append(res, byte(num1-num2+'0'))
		} else {
			// 需要借位
			minus++
			num1 += 2
			res = append(res, byte(num1-num2+'0'))
		}
		p1--
		p2--
	}
	// 负数标识
	flag := false
	if minus > 0 {
		res = append(res, byte(minus%2))
		minus >>= 1
	}
	// 翻转
	p3, p4 := 0, len(res)-1
	for p3 < p4 {
		res[p3], res[p4] = res[p4], res[p3]
		p3++
		p4--
	}
	if flag {
		res = append([]byte("-"), res...)
	}
	return string(res)
}
