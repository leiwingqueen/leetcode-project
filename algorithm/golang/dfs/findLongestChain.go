package dfs

// 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
//
//现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
//
//给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
//
//
//
//示例：
//
//输入：[[1,2], [2,3], [3,4]]
//输出：2
//解释：最长的数对链是 [1,2] -> [3,4]
//
//
//提示：
//
//给出数对的个数在 [1, 1000] 范围内。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-length-of-pair-chain
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 构造图+dfs+记忆
func findLongestChain(pairs [][]int) int {
	n := len(pairs)
	graph := make([][]bool, n)
	for i := 0; i < n; i++ {
		graph[i] = make([]bool, n)
	}
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			p1 := pairs[i]
			p2 := pairs[j]
			if p1[1] < p2[0] {
				graph[i][j] = true
			} else if p2[1] < p1[0] {
				graph[j][i] = true
			}
		}
	}
	cache := make([]int, n)
	for i := 0; i < n; i++ {
		cache[i] = -1
	}
	mx := 0
	var dfs func(start int) int
	dfs = func(start int) int {
		if cache[start] >= 0 {
			return cache[start]
		}
		res := 1
		for i := 0; i < n; i++ {
			if graph[start][i] {
				sub := dfs(i)
				if sub+1 > res {
					res = sub + 1
				}
			}
		}
		if res > mx {
			mx = res
		}
		cache[start] = res
		return res
	}
	for i := 0; i < n; i++ {
		dfs(i)
	}
	return mx
}
