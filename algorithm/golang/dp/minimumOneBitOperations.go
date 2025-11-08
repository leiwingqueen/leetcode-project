package dp

import "fmt"

// 给你一个整数 n，你需要重复执行多次下述操作将其转换为 0 ：
//
//翻转 n 的二进制表示中最右侧位（第 0 位）。
//如果第 (i-1) 位为 1 且从第 (i-2) 位到第 0 位都为 0，则翻转 n 的二进制表示中的第 i 位。
//返回将 n 转换为 0 的最小操作次数。
//
//
//
//示例 1：
//
//输入：n = 3
//输出：2
//解释：3 的二进制表示为 "11"
//"11" -> "01" ，执行的是第 2 种操作，因为第 0 位为 1 。
//"01" -> "00" ，执行的是第 1 种操作。
//示例 2：
//
//输入：n = 6
//输出：4
//解释：6 的二进制表示为 "110".
//"110" -> "010" ，执行的是第 2 种操作，因为第 1 位为 1 ，第 0 到 0 位为 0 。
//"010" -> "011" ，执行的是第 1 种操作。
//"011" -> "001" ，执行的是第 2 种操作，因为第 0 位为 1 。
//"001" -> "000" ，执行的是第 1 种操作。
//
//
//提示：
//
//0 <= n <= 109

// 典型的DP题目，先尝试记忆
// 错误，死循环，应该用bfs比较合适
func minimumOneBitOperations(n int) int {
	mem := make(map[int]int)
	var dfs func(num int) int
	dfs = func(num int) int {
		fmt.Println(num)
		if num == 0 {
			return 0
		}
		if v, ok := mem[num]; ok {
			return v
		}
		// 先尝试方法1
		r1 := dfs(num ^ 0x01)
		// 再尝试方法2
		i := 0
		for n&(1<<i) == 0 {
			i++
		}
		r2 := dfs(num ^ (1 << i))
		res := min(r1, r2) + 1
		mem[num] = res
		return res
	}
	return dfs(n)
}

// 超时
func minimumOneBitOperations2(n int) int {
	var queue []int
	queue = append(queue, n)
	visit := make(map[int]bool)
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			num := queue[i]
			if num == 0 {
				return depth
			}
			// 先尝试方法1
			n1 := num ^ 0x01
			if !visit[n1] {
				queue = append(queue, n1)
				visit[n1] = true
			}
			// 再尝试方法2
			j := 0
			for num&(1<<j) == 0 {
				j++
			}
			n2 := num ^ (1 << (j + 1))
			if !visit[n2] {
				queue = append(queue, n2)
				visit[n2] = true
			}
		}
		queue = queue[size:]
		depth++
	}
	return -1
}
