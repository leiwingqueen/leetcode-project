package math

// 给你一个正整数 n ，开始时，它放在桌面上。在 109 天内，每天都要执行下述步骤：
//
//对于出现在桌面上的每个数字 x ，找出符合 1 <= i <= n 且满足 x % i == 1 的所有数字 i 。
//然后，将这些数字放在桌面上。
//返回在 109 天之后，出现在桌面上的 不同 整数的数目。
//
//注意：
//
//一旦数字放在桌面上，则会一直保留直到结束。
//% 表示取余运算。例如，14 % 3 等于 2 。
//
//
//示例 1：
//
//输入：n = 5
//输出：4
//解释：最开始，5 在桌面上。
//第二天，2 和 4 也出现在桌面上，因为 5 % 2 == 1 且 5 % 4 == 1 。
//再过一天 3 也出现在桌面上，因为 4 % 3 == 1 。
//在十亿天结束时，桌面上的不同数字有 2 、3 、4 、5 。
//示例 2：
//
//输入：n = 3
//输出：2
//解释：
//因为 3 % 2 == 1 ，2 也出现在桌面上。
//在十亿天结束时，桌面上的不同数字只有两个：2 和 3 。
//
//
//提示：
//
//1 <= n <= 100

func distinctIntegers(n int) int {
	visit := make([]bool, n+1)
	visit[n] = true
	var queue []int
	queue = append(queue, n)
	res := 1
	for len(queue) > 0 {
		size := len(queue)
		for _, next := range queue[:size] {
			for i := 1; i < n; i++ {
				if !visit[i] && next%i == 1 {
					queue = append(queue, i)
					visit[i] = true
					res++
				}
			}
		}
		queue = queue[size:]
	}
	return res
}
