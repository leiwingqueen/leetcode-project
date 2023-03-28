package dp

import "strings"

// 给出两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为子序列的最短字符串。如果答案不止一个，则可以返回满足条件的任意一个答案。
//
//（如果从字符串 T 中删除一些字符（也可能不删除，并且选出的这些字符可以位于 T 中的 任意位置），可以得到字符串 S，那么 S 就是 T 的子序列）
//
//
//
//示例：
//
//输入：str1 = "abac", str2 = "cab"
//输出："cabac"
//解释：
//str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
//str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
//最终我们给出的答案是满足上述属性的最短字符串。
//
//
//提示：
//
//1 <= str1.length, str2.length <= 1000
//str1 和 str2 都由小写英文字母组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shortest-common-supersequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 等价于求str1和str2的最大公共子串，不正确
func shortestCommonSupersequence(str1 string, str2 string) string {
	mx := 0
	res := str1 + str2
	for i := range str1 {
		for j := range str2 {
			k := 0
			for ; i+k < len(str1) && j+k < len(str2); k++ {
				if str1[i+k] != str2[j+k] {
					break
				}
			}
			if k > mx {
				mx = k
				// 拼接
				builder := strings.Builder{}
				if i > 0 {
					builder.WriteString(str1[0:i])
				}
				if j > 0 {
					builder.WriteString(str2[0:j])
				}
				builder.WriteString(str1[i:])
				if j+k < len(str2) {
					builder.WriteString(str2[j+k:])
				}
				res = builder.String()
			}
		}
	}
	return res
}

// 所以应该是求str1和str2的最大公共子序列?
// 还是错误，是否应该遍历所有开始匹配的位置？
func shortestCommonSupersequence2(str1 string, str2 string) string {
	r1 := strings.Builder{}
	i, j := 0, 0
	for i < len(str1) {
		if j == len(str2) {
			r1.WriteString(str1[i:])
			break
		}
		if str1[i] == str2[j] {
			r1.WriteByte(str1[i])
			i++
			j++
		} else {
			r1.WriteByte(str2[j])
			j++
		}
	}
	r2 := strings.Builder{}
	i, j = 0, 0
	for j < len(str2) {
		if i == len(str1) {
			r2.WriteString(str2[j:])
			break
		}
		if str1[i] == str2[j] {
			r2.WriteByte(str2[j])
			i++
			j++
		} else {
			r2.WriteByte(str1[i])
			i++
		}
	}
	if r1.Len() < r2.Len() {
		return r1.String()
	} else {
		return r2.String()
	}
}

// 在上面的基础上再进行修改，还是错
func shortestCommonSupersequence3(str1 string, str2 string) string {
	check := func(p1, p2 int) string {
		prefix := strings.Builder{}
		if p1 > 0 {
			prefix.WriteString(str1[0:p1])
		}
		if p2 > 0 {
			prefix.WriteString(str2[0:p2])
		}
		i, j := p1, p2
		r1 := strings.Builder{}
		for i < len(str1) {
			if j == len(str2) {
				r1.WriteString(str1[i:])
				break
			}
			if str1[i] == str2[j] {
				r1.WriteByte(str1[i])
				i++
				j++
			} else {
				r1.WriteByte(str2[j])
				j++
			}
		}
		if j < len(str2) {
			r1.WriteString(str2[j:])
		}
		i, j = p1, p2
		r2 := strings.Builder{}
		for j < len(str2) {
			if i == len(str1) {
				r2.WriteString(str2[j:])
				break
			}
			if str1[i] == str2[j] {
				r2.WriteByte(str2[j])
				i++
				j++
			} else {
				r2.WriteByte(str1[i])
				i++
			}
		}
		if i < len(str1) {
			r2.WriteString(str1[i:])
		}
		if r1.Len() < r2.Len() {
			return prefix.String() + r1.String()
		} else {
			return prefix.String() + r2.String()
		}
	}
	res := str1 + str2
	for p1 := 0; p1 < len(str1); p1++ {
		for p2 := 0; p2 < len(str2); p2++ {
			sub := check(p1, p2)
			if len(sub) < len(res) {
				res = sub
			}
		}
	}
	return res
}
