package dfs

// 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
//
//例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
//给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
//
//
//示例 1:
//
//输入: n = 1, k = 1
//输出: 0
//解释: 第一行：0
//示例 2:
//
//输入: n = 2, k = 1
//输出: 0
//解释:
//第一行: 0
//第二行: 01
//示例 3:
//
//输入: n = 2, k = 2
//输出: 1
//解释:
//第一行: 0
//第二行: 01
//
//
//提示:
//
//1 <= n <= 30
//1 <= k <= 2n - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/k-th-symbol-in-grammar
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func kthGrammar(n int, k int) int {
	var dfs func(n int, k int) int
	dfs = func(n int, k int) int {
		if n == 0 {
			return 0
		}
		last := dfs(n-1, k/2)
		if last == 0 {
			if k%2 == 0 {
				return 0
			} else {
				return 1
			}
		} else {
			if k%2 == 0 {
				return 1
			} else {
				return 0
			}
		}
	}
	return dfs(n-1, k-1)
}

// 优化写法
func kthGrammar2(n int, k int) int {
	var dfs func(n int, k int) int
	dfs = func(n int, k int) int {
		if n == 0 {
			return 0
		}
		last := dfs(n-1, k/2)
		/**if last == 0 && k%2 == 0 || last == 1 && k%2 == 1 {
			return 0
		} else {
			return 1
		}*/
		// 可以再简化成下面的写法
		return last ^ (k & 0b1)
	}
	return dfs(n-1, k-1)
}
