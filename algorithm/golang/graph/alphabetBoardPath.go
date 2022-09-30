package graph

func alphabetBoardPath(target string) string {
	mp := make(map[byte][]int)
	for i := 0; i < 26; i++ {
		ch := byte(i + 'a')
		mp[ch] = []int{i / 5, i % 5}
	}
	rowSize := []int{5, 5, 5, 5, 5, 1}
	var res []byte
	pre := byte('a')
	for i := 0; i < len(target); i++ {
		ch := target[i]
		x := mp[ch][0] - mp[pre][0]
		y := mp[ch][1] - mp[pre][1]
		cur := []int{mp[pre][0], mp[pre][1]}
		for x != 0 || y != 0 {
			if x > 0 && cur[1] < rowSize[cur[0]+1] || x < 0 {
				if x > 0 {
					res = append(res, 'D')
					x--
					cur[0]++
				} else {
					res = append(res, 'U')
					x++
					cur[0]--
				}
			} else {
				if y > 0 {
					res = append(res, 'R')
					y--
					cur[1]++
				} else {
					res = append(res, 'L')
					y++
					cur[1]--
				}
			}
		}
		pre = ch
		res = append(res, '!')
	}
	return string(res)
}
