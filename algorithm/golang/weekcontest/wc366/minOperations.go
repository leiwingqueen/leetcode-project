package wc366

// 贪心，然而并不是最优解
func minOperations(s1 string, s2 string, x int) int {
	n := len(s1)
	p1, p2 := 0, 0
	res := 0
	for p2 < n {
		for p1 < n && s1[p1] == s2[p1] {
			p1++
		}
		if p1 == n {
			return res
		}
		p2 = p1 + 1
		for p2 < n && s1[p2] == s2[p2] {
			p2++
		}
		if p2 == n {
			return -1
		}
		if p2-p1 < x {
			res += p2 - p1
		} else {
			res += x
		}
		p1 = p2 + 1
	}
	return res
}

// 试试贪心2？
func minOperations2(s1 string, s2 string, x int) int {
	n := len(s1)
	var arr []int
	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			arr = append(arr, i)
		}
	}
	if len(arr)%2 == 1 {
		return -1
	}
	// 先把相邻的数字去掉
	var tmp []int
	for i := 0; i < n-1; i++ {
		if arr[i] == arr[i+1] {
			i++
		} else {
			tmp = append(tmp, arr[i])
		}
	}
	// 然后按贪心处理剩下的数字
	// 不行 还是有问题
	return 0
}
