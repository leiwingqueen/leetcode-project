package string

//如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0
//个字符。）
//
// 给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串
//pattern 匹配时， answer[i] 才为 true，否则为 false。
//
//
//
// 示例 1：
//
// 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"]
//, pattern = "FB"
//输出：[true,false,true,true,false]
//示例：
//"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
//"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
//"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
//
// 示例 2：
//
// 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"]
//, pattern = "FoBa"
//输出：[true,false,true,false,false]
//解释：
//"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
//"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
//
//
// 示例 3：
//
// 输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"]
//, pattern = "FoBaT"
//输入：[false,true,false,false,false]
//解释：
//"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
//
//
//
//
// 提示：
//
//
// 1 <= queries.length <= 100
// 1 <= queries[i].length <= 100
// 1 <= pattern.length <= 100
// 所有字符串都仅由大写和小写英文字母组成。
//
//
// Related Topics 字典树 双指针 字符串 字符串匹配 👍 84 👎 0

func camelMatch(queries []string, pattern string) []bool {
	check := func(s string) bool {
		p1, p2 := 0, 0
		n := len(s)
		for p1 < n {
			if p2 == len(pattern) {
				for p1 < n {
					if s[p1] >= 'A' && s[p1] <= 'Z' {
						return false
					}
					p1++
				}
			} else {
				for p1 < n && s[p1] != pattern[p2] {
					if s[p1] >= 'A' && s[p1] <= 'Z' {
						return false
					}
					p1++
				}
			}
			if p1 == n {
				return p2 == len(pattern)
			}
			if p2 == len(pattern) {
				return false
			}
			p1++
			p2++
		}
		return p2 == len(pattern)
	}
	res := make([]bool, len(queries))
	for i, query := range queries {
		res[i] = check(query)
	}
	return res
}
