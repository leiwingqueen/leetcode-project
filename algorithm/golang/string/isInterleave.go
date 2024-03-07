package string

// Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//
//An interleaving of two strings s and t is a configuration where s and t are divided into n and m
//substrings
// respectively, such that:
//
//s = s1 + s2 + ... + sn
//t = t1 + t2 + ... + tm
//|n - m| <= 1
//The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
//Note: a + b is the concatenation of strings a and b.
//
//
//
//Example 1:
//
//
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//Output: true
//Explanation: One way to obtain s3 is:
//Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
//Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
//Since s3 can be obtained by interleaving s1 and s2, we return true.
//Example 2:
//
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//Output: false
//Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
//Example 3:
//
//Input: s1 = "", s2 = "", s3 = ""
//Output: true
//
//
//Constraints:
//
//0 <= s1.length, s2.length <= 100
//0 <= s3.length <= 200
//s1, s2, and s3 consist of lowercase English letters.
//
//
//Follow up: Could you solve it using only O(s2.length) additional memory space?

// 定义f(i,j)为前i个s1的字符和前j个s2的字符能够构成s3的前i+j个字符
// f(i,j)=f(i-1,j)||f(i,j-1)
func isInterleave(s1 string, s2 string, s3 string) bool {
	n, m := len(s1), len(s2)
	if n+m != len(s3) {
		return false
	}
	dp := make([][]bool, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]bool, m+1)
	}
	dp[0][0] = true
	// 初始化
	for i := 1; i <= m && s2[i-1] == s3[i-1]; i++ {
		dp[0][i] = true
	}
	for i := 1; i <= n && s1[i-1] == s3[i-1]; i++ {
		dp[i][0] = true
	}
	// dp迭代
	for i := 1; i <= n; i++ {
		for j := 1; j <= m; j++ {
			if s1[i-1] == s3[i+j-1] && dp[i-1][j] ||
				s2[j-1] == s3[i+j-1] && dp[i][j-1] {
				dp[i][j] = true
			}
		}
	}
	return dp[n][m]
}

// 空间优化
func isInterleave2(s1 string, s2 string, s3 string) bool {
	n, m := len(s1), len(s2)
	if n+m != len(s3) {
		return false
	}
	pre := make([]bool, m+1)
	dp := make([]bool, m+1)
	pre[0] = true
	// 初始化
	for i := 1; i <= m && s2[i-1] == s3[i-1]; i++ {
		pre[i] = true
	}
	// dp迭代
	for i := 1; i <= n; i++ {
		dp[0] = pre[0] && s1[i-1] == s3[i-1]
		for j := 1; j <= m; j++ {
			if s1[i-1] == s3[i+j-1] && pre[j] ||
				s2[j-1] == s3[i+j-1] && dp[j-1] {
				dp[j] = true
			} else {
				dp[j] = false
			}
		}
		// 拷贝dp到pre
		copy(pre, dp)
	}
	return pre[m]
}
