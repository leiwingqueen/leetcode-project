package bwc121

// 给你两个正整数 x 和 y 。
//
//一次操作中，你可以执行以下四种操作之一：
//
//如果 x 是 11 的倍数，将 x 除以 11 。
//如果 x 是 5 的倍数，将 x 除以 5 。
//将 x 减 1 。
//将 x 加 1 。
//请你返回让 x 和 y 相等的 最少 操作次数。
//
//
//
//示例 1：
//
//输入：x = 26, y = 1
//输出：3
//解释：我们可以通过以下操作将 26 变为 1 ：
//1. 将 x 减 1
//2. 将 x 除以 5
//3. 将 x 除以 5
//将 26 变为 1 最少需要 3 次操作。
//示例 2：
//
//输入：x = 54, y = 2
//输出：4
//解释：我们可以通过以下操作将 54 变为 2 ：
//1. 将 x 加 1
//2. 将 x 除以 11
//3. 将 x 除以 5
//4. 将 x 加 1
//将 54 变为 2 最少需要 4 次操作。
//示例 3：
//
//输入：x = 25, y = 30
//输出：5
//解释：我们可以通过以下操作将 25 变为 30 ：
//1. 将 x 加 1
//2. 将 x 加 1
//3. 将 x 加 1
//4. 将 x 加 1
//5. 将 x 加 1
//将 25 变为 30 最少需要 5 次操作。
//
//
//提示：
//
//1 <= x, y <= 104

func minimumOperationsToMakeEqual(x int, y int) int {
	var dfs func(a, b int) int
	dfs = func(a, b int) int {
		if a == b {
			return 0
		}
		if a < b {
			a, b = b, a
		}
		res := 0
		if a%11 == 0 {
			res = dfs(a/11, b) + 1
		}
		if a%5 == 0 {
			sub := dfs(a/5, b) + 1
			if sub < res {
				res = sub
			}
		}
		s1 := dfs(a-1, b) + 1
		s2 := dfs(a+1, b) + 1
		if s1 < res {
			res = s1
		}
		if s2 < res {
			res = s2
		}
		return res
	}
	return dfs(x, y)
}
