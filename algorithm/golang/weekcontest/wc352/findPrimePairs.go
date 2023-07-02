package wc352

// 给你一个整数 n 。如果两个整数 x 和 y 满足下述条件，则认为二者形成一个质数对：
//
//1 <= x <= y <= n
//x + y == n
//x 和 y 都是质数
//请你以二维有序列表的形式返回符合题目要求的所有 [xi, yi] ，列表需要按 xi 的 非递减顺序 排序。如果不存在符合要求的质数对，则返回一个空数组。
//
//注意：质数是大于 1 的自然数，并且只有两个因子，即它本身和 1 。
//
//
//
//示例 1：
//
//输入：n = 10
//输出：[[3,7],[5,5]]
//解释：在这个例子中，存在满足条件的两个质数对。
//这两个质数对分别是 [3,7] 和 [5,5]，按照题面描述中的方式排序后返回。
//示例 2：
//
//输入：n = 2
//输出：[]
//解释：可以证明不存在和为 2 的质数对，所以返回一个空数组。
//
//
//提示：
//
//1 <= n <= 106

func findPrimePairs(n int) [][]int {
	if n < 4 {
		return [][]int{}
	}
	// 预计算<=n的所有质数
	primes := make(map[int]struct{})
	flags := make([]bool, n+1)
	for i := 2; i <= n; i++ {
		if flags[i] {
			continue
		}
		primes[i] = struct{}{}
		for j := i; j <= n; j += i {
			flags[j] = true
		}
	}
	var res [][]int
	for i := 2; i <= n/2; i++ {
		if _, exist := primes[i]; exist {
			if _, e2 := primes[n-i]; e2 {
				res = append(res, []int{i, n - i})
			}
		}
	}
	return res
}
