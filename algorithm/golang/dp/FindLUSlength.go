package dp

//给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列  。如果不存在，则返回 -1 。
//
//「最长特殊序列」 定义如下：该序列为 某字符串独有的最长子序列（即不能是其他字符串的子序列） 。
//
//字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。
//
//例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。 "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
//
//
//示例 1：
//
//输入: a = "aba", b = "cdc"
//输出: 3
//解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
//示例 2：
//
//输入：a = "aaa", b = "bbb"
//输出：3
//解释: 最长特殊序列是 "aaa" 和 "bbb" 。
//示例 3：
//
//输入：a = "aaa", b = "aaa"
//输出：-1
//解释: 字符串 a 的每个子序列也是字符串 b 的每个子序列。同样，字符串 b 的每个子序列也是字符串 a 的子序列。
//
//
//提示：
//
//1 <= a.length, b.length <= 100
//a 和 b 由小写英文字母组成
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-uncommon-subsequence-i
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
反过来求，先求公共子序列的最大长度，然后反过来求特殊子序列的最大长度

好像逻辑上不通？
*/
func findLUSlength(a string, b string) int {
	n1 := len(a)
	n2 := len(b)
	dp := make([][]int, n1+1)
	dp[0] = make([]int, n2+1)
	for i := 1; i <= n1; i++ {
		dp[i] = make([]int, n2+1)
		for j := 1; j <= n2; j++ {
			dp[i][j] = dp[i-1][j]
			if a[i-1] == b[i-1] && dp[i-1][j-1]+1 > dp[i][j] {
				dp[i][j] = dp[i-1][j-1] + 1
			}
		}
	}
	l := n1
	if n2 < l {
		l = n2
	}
	if l-dp[n1][n2] == 0 {
		return -1
	} else {
		return l - dp[n1][n2]
	}
}

//还是不对
func findLUSlength2(a string, b string) int {
	n1 := len(a)
	n2 := len(b)
	dp := make([][]int, n1+1)
	for i := 0; i <= n1; i++ {
		dp[i] = make([]int, n2+1)
	}
	for i := 0; i <= n2; i++ {
		dp[0][i] = i
	}
	for i := 1; i <= n1; i++ {
		dp[i][0] = i
	}
	for i := 1; i <= n1; i++ {
		for j := 1; j <= n2; j++ {
			if a[i-1] != b[j-1] {
				dp[i][j] = max(i, j)
			} else {
				dp[i][j] = max(dp[i-1][j], dp[i][j-1])
			}
		}
	}
	if dp[n1][n2] == 0 {
		return -1
	} else {
		return dp[n1][n2]
	}
}

//看了下答案，果然我是个智障
func findLUSlength3(a string, b string) int {
	n1 := len(a)
	n2 := len(b)
	if a != b {
		return max(n1, n2)
	} else {
		return -1
	}
}
