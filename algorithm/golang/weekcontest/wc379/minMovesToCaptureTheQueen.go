package wc379

// 分情况讨论，其中最终的结果只有1或者2
// 假设车、象、皇后分别表示为T1,T2,T3
func minMovesToCaptureTheQueen(a int, b int, c int, d int, e int, f int) int {
	abs := func(x int) int {
		if x < 0 {
			return -x
		} else {
			return x
		}
	}
	// 1. T2和T3在同一个对角线上
	if abs(c-e) == abs(d-f) {
		// 1.1 判断T1是否在T2和T3之间
		// if true return 2,else return 1
	}
	// 2. T1和T3在同一个水平线上
	// 2.1 T2是否在T1和T2之间，if true return 2 else return 1
	// 3. 其他 return 2
	return 0
}
