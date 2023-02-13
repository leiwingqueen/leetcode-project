package prefixSum

// 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
//
//假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
//
//
//
//给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
//
//你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
//
//请返回待替换子串的最小可能长度。
//
//如果原字符串自身就是一个平衡字符串，则返回 0。
//
//
//
//示例 1：
//
//输入：s = "QWER"
//输出：0
//解释：s 已经是平衡的了。
//示例 2：
//
//输入：s = "QQWE"
//输出：1
//解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
//示例 3：
//
//输入：s = "QQQW"
//输出：2
//解释：我们可以把前面的 "QQ" 替换成 "ER"。
//示例 4：
//
//输入：s = "QQQQ"
//输出：3
//解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
//
//
//提示：
//
//1 <= s.length <= 10^5
//s.length 是 4 的倍数
//s 中只含有 'Q', 'W', 'E', 'R' 四种字符
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/replace-the-substring-for-balanced-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 二分查找+前缀和
// 时间复杂度O(nlog(n))，空间复杂度O(n)
func balancedString(s string) int {
	// 计算每个字母的前缀和，为什么？
	// 假设我们要判断长度为k的子串替换是否能够解决，假设子串的下标范围为[i,j]，那么我们需要判断[0,i)和[j+1,n)的每个字符的总和是否小于avg(avg是平均出现次数)
	// 这里我们可以用前缀和来快速计算
	n := len(s)
	prefix := make([][]int, 4)
	for i := 0; i < 4; i++ {
		prefix[i] = make([]int, n+1)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 4; j++ {
			prefix[j][i+1] = prefix[j][i]
		}
		if s[i] == 'Q' {
			prefix[0][i+1]++
		} else if s[i] == 'W' {
			prefix[1][i+1]++
		} else if s[i] == 'E' {
			prefix[2][i+1]++
		} else {
			prefix[3][i+1]++
		}
	}
	// 特殊场景，每个字符出现次数都相等的场景
	avg := n / 4
	flag := true
	for i := 0; i < 4; i++ {
		if prefix[i][n] != avg {
			flag = false
		}
	}
	if flag {
		return 0
	}
	// 二分查找最短的长度，check为检查的过程,需要遍历从下标从[0,n-k]
	check := func(k int) bool {
		if k == n {
			return true
		}
		for i := 0; i <= n-k; i++ {
			f := true
			for j := 0; j < 4; j++ {
				sum := prefix[j][i] + prefix[j][n] - prefix[j][i+k]
				if sum > avg {
					f = false
					break
				}
			}
			if f {
				return true
			}
		}
		return false
	}
	l := 1
	r := n
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
