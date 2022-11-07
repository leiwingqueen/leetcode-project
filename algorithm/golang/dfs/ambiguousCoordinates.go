package dfs

import "fmt"

// 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
//
//原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
//
//最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
//
//
//
//示例 1:
//输入: "(123)"
//输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
//示例 2:
//输入: "(00011)"
//输出:  ["(0.001, 1)", "(0, 0.011)"]
//解释:
//0.0, 00, 0001 或 00.01 是不被允许的。
//示例 3:
//输入: "(0123)"
//输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
//示例 4:
//输入: "(100)"
//输出: [(10, 0)]
//解释:
//1.0 是不被允许的。
//
//
//提示:
//
//4 <= S.length <= 12.
//S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/ambiguous-coordinates
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 枚举所有场景，重点是把不合法的场景去掉
func ambiguousCoordinates(s string) []string {
	n := len(s)
	var dfs func(str string) []string
	dfs = func(str string) []string {
		if len(str) == 1 {
			return []string{str}
		}
		// 以0结尾，表示前面不存在小数点
		if str[len(str)-1] == '0' {
			if str[0] == '0' {
				return []string{}
			} else {
				return []string{str}
			}
		} else {
			if str[0] == '0' {
				return []string{fmt.Sprintf("0.%s", str[1:])}
			} else {
				arr := make([]string, 0)
				arr = append(arr, str)
				for i := 0; i < len(str)-1; i++ {
					arr = append(arr, fmt.Sprintf("%s.%s", str[0:i+1], str[i+1:]))
				}
				return arr
			}
		}
	}
	var res []string
	for i := 1; i < n-2; i++ {
		s1 := s[1 : i+1]
		s2 := s[i+1 : n-1]
		arr1 := dfs(s1)
		arr2 := dfs(s2)
		if len(arr1) >= 1 && len(arr2) >= 1 {
			for _, d1 := range arr1 {
				for _, d2 := range arr2 {
					res = append(res, fmt.Sprintf("(%s, %s)", d1, d2))
				}
			}
		}
	}
	return res
}
