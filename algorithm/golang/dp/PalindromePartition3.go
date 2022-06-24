package dp

import "math"

/**
1278. 分割回文串 III

给你一个由小写字母组成的字符串 s，和一个整数 k。

请你按下面的要求分割字符串：

首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
请返回以这种方式分割字符串所需修改的最少字符数。

 

示例 1：

输入：s = "abc", k = 2
输出：1
解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。
示例 2：

输入：s = "aabbc", k = 3
输出：0
解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。
示例 3：

输入：s = "leetcode", k = 8
输出：0
 

提示：

1 <= k <= s.length <= 100
s 中只含有小写英文字母。
通过次数3,017提交次数5,125


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-partitioning-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

/**
时间复杂度O(n^3*k)
*/
func palindromePartition(s string, k int) int {
	dp := make([][]int, len(s)+1)
	for i := 0; i <= len(s); i++ {
		dp[i] = make([]int, k+1)
	}
	//初始化
	for i := 1; i <= len(s); i++ {
		dp[i][1] = makePal(s, 0, i-1)
	}
	for i := 2; i <= len(s); i++ {
		for j := 2; j <= k && j <= i; j++ {
			min := math.MaxInt32
			for m := i - 1; m > 0 && m >= j-1; m-- {
				r := dp[m][j-1] + makePal(s, m, i-1)
				if r < min {
					min = r
				}
			}
			dp[i][j] = min
		}
	}
	return dp[len(s)][k]
}

//TODO:这里可以使用DP进行预处理
func makePal(s string, l int, r int) int {
	cnt := 0
	for l < r {
		if s[l] != s[r] {
			cnt++
		}
		l++
		r--
	}
	return cnt
}

/**
代码优化，增加预处理
*/
func palindromePartition2(s string, k int) int {
	//预处理makePal
	cost := make([][]int, len(s))
	for i := 0; i < len(s); i++ {
		cost[i] = make([]int, len(s))
	}
	for i := len(s) - 2; i >= 0; i-- {
		for j := i + 1; j < len(s); j++ {
			cost[i][j] = cost[i+1][j-1]
			if s[i] != s[j] {
				cost[i][j] += 1
			}
		}
	}
	dp := make([][]int, len(s)+1)
	for i := 0; i <= len(s); i++ {
		dp[i] = make([]int, k+1)
	}
	//初始化
	for i := 1; i <= len(s); i++ {
		dp[i][1] = cost[0][i-1]
	}
	for i := 2; i <= len(s); i++ {
		for j := 2; j <= k && j <= i; j++ {
			min := math.MaxInt32
			for m := i - 1; m > 0 && m >= j-1; m-- {
				r := dp[m][j-1] + cost[m][i-1]
				if r < min {
					min = r
				}
			}
			dp[i][j] = min
		}
	}
	return dp[len(s)][k]
}
