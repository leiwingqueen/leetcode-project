package math

// 给定正整数 n，返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。
//
//
//
//示例 1：
//
//输入：n = 20
//输出：1
//解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
//示例 2：
//
//输入：n = 100
//输出：10
//解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
//示例 3：
//
//输入：n = 1000
//输出：262
//
//
//提示：
//
//1 <= n <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/numbers-with-repeated-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 居然勉强过了，这种解法偏穷举的解法
func numDupDigitsAtMostN(n int) int {
	// 检查没有重复的数字
	var dfs func(size int, used []bool, idx int, prefix int) int
	dfs = func(size int, used []bool, idx int, prefix int) int {
		if prefix > n {
			return 0
		}
		if idx == size {
			return 1
		}
		res := 0
		for i := 0; i < 10; i++ {
			if i == 0 && idx == 0 {
				continue
			}
			if !used[i] {
				used[i] = true
				res += dfs(size, used, idx+1, prefix*10+i)
				used[i] = false
			}
		}
		return res
	}
	cnt := 0
	size, num := 0, n
	for num > 0 {
		num /= 10
		size++
	}
	for i := 1; i <= size; i++ {
		cnt += dfs(i, make([]bool, 10), 0, 0)
	}
	return n - cnt
}

// 优化解法
func numDupDigitsAtMostN2(n int) int {
	cal := func(size int) int {
		res := 1
		for i := 0; i < size; i++ {
			if i == 0 {
				res *= 9
			} else {
				res *= 10 - i
			}
		}
		return res
	}
	cnt := 0
	size, num := 0, n
	for num > 0 {
		num /= 10
		size++
	}
	arr := make([]int, size)
	num = n
	for i := size - 1; i >= 0; i-- {
		arr[i] = num % 10
		num /= 10
	}
	var dfs func(size int, used []bool, idx int, eq bool, free int) int
	dfs = func(size int, used []bool, idx int, eq bool, free int) int {
		if idx == size {
			return 1
		}
		if !eq {
			return free * dfs(size, used, idx+1, false, free-1)
		} else {
			res := 0
			for i := 0; i < 10; i++ {
				if i == 0 && idx == 0 {
					continue
				}
				if !used[i] && i <= arr[idx] {
					if i < arr[idx] {
						eq = false
					} else if i == arr[idx] {
						eq = true
					}
					used[i] = true
					res += dfs(size, used, idx+1, eq, free-1)
					used[i] = false
				}
			}
			return res
		}
	}

	for i := 1; i < size; i++ {
		cnt += cal(i)
	}
	cnt += dfs(size, make([]bool, 10), 0, true, 10)
	return n - cnt
}
