package bwc144

func shiftDistance(s string, t string, nextCost []int, previousCost []int) int64 {
	// 提前计算两个字母的距离
	dis1 := make([][]int, 26)
	for i := 0; i < 26; i++ {
		dis1[i] = make([]int, 26)
	}
	// f(i,j)=f(i,j-1)+next[j-1]
	for i := 0; i < 26; i++ {
		for j := 1; j < 26; j++ {
			l := (i + j) % 26
			// 计算f(i,l)
			pre := (l + 26 - 1) % 26
			dis1[i][l] = dis1[i][pre] + nextCost[pre]
		}
	}
	dis2 := make([][]int, 26)
	for i := 0; i < 26; i++ {
		dis2[i] = make([]int, 26)
	}
	// f(i,j)=f(i,j-1)+next[j-1]
	for i := 0; i < 26; i++ {
		for j := 1; j < 26; j++ {
			l := (i - j + 26) % 26
			// 计算f(i,l)
			pre := (l + 1) % 26
			dis2[i][l] = dis2[i][pre] + previousCost[pre]
		}
	}
	n := len(s)
	dis := int64(0)
	for i := 0; i < n; i++ {
		idx1, idx2 := s[i]-'a', t[i]-'a'
		dis += int64(min(dis1[idx1][idx2], dis2[idx1][idx2]))
	}
	return dis
}
