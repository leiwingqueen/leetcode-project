package bwc121

import "fmt"

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
	if x <= y {
		return y - x
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	mem := make(map[int]int)
	var dfs func(num int, mx int) int
	dfs = func(num int, mx int) int {
		fmt.Println(fmt.Sprintf("%d", num))
		if num <= y {
			return y - num
		}
		if v, ok := mem[num]; ok {
			return v
		}
		res := num - y
		defer func() {
			mem[num] = res
		}()
		res = min(res, dfs(num+1, mx-1)+1)
		res = min(res, dfs(num-1, mx-1)+1)
		if num%11 == 0 && num != 0 {
			res = min(res, dfs(num/11, mx-1)+1)
		}
		if num%5 == 0 && num != 0 {
			res = min(res, dfs(num/5, mx-1)+1)
		}
		return res
	}
	return dfs(x, x-y)
}
