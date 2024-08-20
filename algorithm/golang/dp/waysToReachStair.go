package dp

import "math"

// 给你有一个 非负 整数 k 。有一个无限长度的台阶，最低 一层编号为 0 。
//
// Alice 有一个整数 jump ，一开始值为 0 。Alice 从台阶 1 开始，可以使用 任意 次操作，目标是到达第 k 级台阶。假设 Alice 位于台阶 i ，一次 操作 中，Alice 可以：
//
// 向下走一级到 i - 1 ，但该操作 不能 连续使用，如果在台阶第 0 级也不能使用。
// 向上走到台阶 i + 2jump 处，然后 jump 变为 jump + 1 。
// 请你返回 Alice 到达台阶 k 处的总方案数。
//
// 注意，Alice 可能到达台阶 k 处后，通过一些操作重新回到台阶 k 处，这视为不同的方案。
//
// 示例 1：
//
// 输入：k = 0
//
// 输出：2
//
// 解释：
//
// 2 种到达台阶 0 的方案为：
//
// Alice 从台阶 1 开始。
// 执行第一种操作，从台阶 1 向下走到台阶 0 。
// Alice 从台阶 1 开始。
// 执行第一种操作，从台阶 1 向下走到台阶 0 。
// 执行第二种操作，向上走 20 级台阶到台阶 1 。
// 执行第一种操作，从台阶 1 向下走到台阶 0 。
// 示例 2：
//
// 输入：k = 1
//
// 输出：4
//
// 解释：
//
// 4 种到达台阶 1 的方案为：
//
// Alice 从台阶 1 开始，已经到达台阶 1 。
// Alice 从台阶 1 开始。
// 执行第一种操作，从台阶 1 向下走到台阶 0 。
// 执行第二种操作，向上走 20 级台阶到台阶 1 。
// Alice 从台阶 1 开始。
// 执行第二种操作，向上走 20 级台阶到台阶 2 。
// 执行第一种操作，向下走 1 级台阶到台阶 1 。
// Alice 从台阶 1 开始。
// 执行第一种操作，从台阶 1 向下走到台阶 0 。
// 执行第二种操作，向上走 20 级台阶到台阶 1 。
// 执行第一种操作，向下走 1 级台阶到台阶 0 。
// 执行第二种操作，向上走 21 级台阶到台阶 2 。
// 执行第一种操作，向下走 1 级台阶到台阶 1 。
//
// 提示：
//
// 0 <= k <= 109

// 回溯，不加任何条件，超时
func waysToReachStair(k int) int {
	var dfs func(source, target, jump int, flag bool) int
	dfs = func(source, target, jump int, flag bool) int {
		// 终止条件 source-1>target
		if source-1 > target {
			return 0
		}
		res := 0
		if source == target {
			res++
		}
		if !flag {
			res += dfs(source-1, target, jump, true)
		}
		res += dfs(source+int(1<<jump), target, jump+1, false)
		return res
	}
	return dfs(1, k, 0, false)
}

// 假设p1为往后推的步数，p2为往前走的步数，我们有p1<=p2+1
// p2的最大值为log(k)，超时
func waysToReachStair2(k int) int {
	p2 := int(math.Sqrt(float64(k)))
	for (1<<p2)+p2+2 <= k {
		p2++
	}
	var dfs func(k1, k2 int, flag bool) int
	dfs = func(k1, k2 int, flag bool) int {
		// 终止条件 source-1>target
		pos := 1 - k1
		if k2 > 0 {
			pos += 1<<k2 - 1
		}
		if pos-1 > k {
			return 0
		}
		res := 0
		if pos == k {
			res++
		}
		if !flag {
			res += dfs(k1+1, k2, true)
		}
		res += dfs(k1, k2+1, false)
		return res
	}
	return dfs(0, 0, false)
}

// 增加记忆，通过489个用例，还是有超时的
func waysToReachStair3(k int) int {
	p2 := int(math.Sqrt(float64(k)))
	for (1<<p2)+p2+2 <= k {
		p2++
	}
	mem := make(map[int]map[int]map[bool]int)
	var dfs func(k1, k2 int, flag bool) int
	dfs = func(k1, k2 int, flag bool) int {
		// 终止条件 source-1>target
		pos := 1 - k1
		if k2 > 0 {
			pos += 1<<k2 - 1
		}
		if pos-1 > k {
			return 0
		}
		if _, ok := mem[k1]; !ok {
			mem[k1] = make(map[int]map[bool]int)
		}
		if _, ok := mem[k1][k2]; !ok {
			mem[k1][k2] = make(map[bool]int)
		}
		if v, ok := mem[k1][k2][flag]; ok {
			return v
		}
		res := 0
		if pos == k {
			res++
		}
		if !flag {
			res += dfs(k1+1, k2, true)
		}
		res += dfs(k1, k2+1, false)
		mem[k1][k2][flag] = res
		return res
	}
	return dfs(0, 0, false)
}
