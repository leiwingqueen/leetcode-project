package wc456

// 给你一个字符串 s，按照以下步骤将其分割为 互不相同的段 ：
//
//从下标 0 开始构建一个段。
//逐字符扩展当前段，直到该段之前未曾出现过。
//只要当前段是唯一的，就将其加入段列表，标记为已经出现过，并从下一个下标开始构建新的段。
//重复上述步骤，直到处理完整个字符串 s。
//返回字符串数组 segments，其中 segments[i] 表示创建的第 i 段。
//
//
//
//示例 1：
//
//输入： s = "abbccccd"
//
//输出： ["a","b","bc","c","cc","d"]
//
//解释：
//
//下标	添加后的段	已经出现过的段	当前段是否已经出现过？	新段	更新后已经出现过的段
//0	"a"	[]	否	""	["a"]
//1	"b"	["a"]	否	""	["a", "b"]
//2	"b"	["a", "b"]	是	"b"	["a", "b"]
//3	"bc"	["a", "b"]	否	""	["a", "b", "bc"]
//4	"c"	["a", "b", "bc"]	否	""	["a", "b", "bc", "c"]
//5	"c"	["a", "b", "bc", "c"]	是	"c"	["a", "b", "bc", "c"]
//6	"cc"	["a", "b", "bc", "c"]	否	""	["a", "b", "bc", "c", "cc"]
//7	"d"	["a", "b", "bc", "c", "cc"]	否	""	["a", "b", "bc", "c", "cc", "d"]
//因此，最终输出为 ["a", "b", "bc", "c", "cc", "d"]。
//
//示例 2：
//
//输入： s = "aaaa"
//
//输出： ["a","aa"]
//
//解释：
//
//下标	添加后的段	已经出现过的段	当前段是否已经出现过？	新段	更新后已经出现过的段
//0	"a"	[]	否	""	["a"]
//1	"a"	["a"]	是	"a"	["a"]
//2	"aa"	["a"]	否	""	["a", "aa"]
//3	"a"	["a", "aa"]	是	"a"	["a", "aa"]
//因此，最终输出为 ["a", "aa"]。
//
//
//
//提示：
//
//1 <= s.length <= 105
//s 仅包含小写英文字母。

func partitionString(s string) []string {
	n := len(s)
	var res []string
	mp := make(map[string]struct{})
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			if _, ok := mp[s[i:j+1]]; !ok {
				mp[s[i:j+1]] = struct{}{}
				res = append(res, s[i:j+1])
				break
			}
		}
	}
	return res
}

// 双指针
func partitionString2(s string) []string {
	n := len(s)
	var res []string
	mp := make(map[string]bool)
	i, j := 0, 0
	for i < n {
		for j < n && (i == j || mp[s[i:j]]) {
			j++
		}
		if !mp[s[i:j]] {
			mp[s[i:j]] = true
			res = append(res, s[i:j])
		}
		i = j
	}
	return res
}
