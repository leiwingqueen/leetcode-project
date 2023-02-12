package wc332

// 超时
func substringXorQueries(s string, queries [][]int) [][]int {
	calLen := func(val int) int {
		if val == 0 {
			return 1
		}
		res := 0
		for val > 0 {
			val >>= 1
			res++
		}
		return res
	}
	find := func(val int) []int {
		size := calLen(val)
		n := len(s)
		l := 0
		r := 0
		num := 0
		for l <= n-size {
			if r-l < size {
				num = (num << 1) + int(s[r]-'0')
				r++
			} else {
				if num == val {
					return []int{l, r - 1}
				} else {
					if s[l] == '1' {
						num -= 1 << (size - 1)
					}
					l++
				}
			}
		}
		return []int{-1, -1}
	}
	res := make([][]int, len(queries))
	for i, query := range queries {
		val := query[0] ^ query[1]
		res[i] = find(val)
	}
	return res
}

func substringXorQueries2(s string, queries [][]int) [][]int {
	mxLen := 30
	n := len(s)
	mp := make(map[int][]int)
	for i := 1; i <= mxLen; i++ {
		for j := 0; j <= n-i; j++ {
			num := 0
			// TODO:这里还是会有一些重复计算
			for l := 0; l < i; l++ {
				num <<= 1
				if s[j+l] == '1' {
					num += 1
				}
			}
			if _, exist := mp[num]; !exist {
				mp[num] = []int{j, j + i - 1}
			}
		}
	}
	res := make([][]int, len(queries))
	for i, query := range queries {
		val := query[0] ^ query[1]
		if r, exist := mp[val]; exist {
			res[i] = r
		} else {
			res[i] = []int{-1, -1}
		}
	}
	return res
}
