package array

// 暴力解法，毫无美感
func minOperations2(boxes string) []int {
	var abs = func(num int) int {
		if num > 0 {
			return num
		} else {
			return -num
		}
	}
	n := len(boxes)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		cnt := 0
		for j := 0; j < n; j++ {
			if boxes[j] != '0' {
				cnt += abs(i - j)
			}
		}
		res[i] = cnt
	}
	return res
}

// 优化解法
func minOperations3(boxes string) []int {
	n := len(boxes)
	pre1 := make([]int, n+1)
	pre2 := make([]int, n+1)
	for i := 0; i < n; i++ {
		pre1[i+1] = pre1[i]
		pre2[i+1] = pre2[i]
		if boxes[i] != '0' {
			pre1[i+1]++
			pre2[i+1] += i
		}
	}
	res := make([]int, n)
	for i := 0; i < n; i++ {
		res[i] = pre1[i]*i - pre2[i] + (pre2[n] - pre2[i]) - (pre1[n]-pre1[i])*i
	}
	return res
}
