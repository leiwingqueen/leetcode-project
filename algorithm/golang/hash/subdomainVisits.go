package hash

import (
	"fmt"
	"strconv"
	"strings"
)

// 网站域名 "discuss.leetcode.com" 由多个子域名组成。顶级域名为 "com" ，二级域名为 "leetcode.com" ，最低一级为 "discuss.leetcode.com" 。当访问域名 "discuss.leetcode.com" 时，同时也会隐式访问其父域名 "leetcode.com" 以及 "com" 。
//
//计数配对域名 是遵循 "rep d1.d2.d3" 或 "rep d1.d2" 格式的一个域名表示，其中 rep 表示访问域名的次数，d1.d2.d3 为域名本身。
//
//例如，"9001 discuss.leetcode.com" 就是一个 计数配对域名 ，表示 discuss.leetcode.com 被访问了 9001 次。
//给你一个 计数配对域名 组成的数组 cpdomains ，解析得到输入中每个子域名对应的 计数配对域名 ，并以数组形式返回。可以按 任意顺序 返回答案。
//
//
//
//示例 1：
//
//输入：cpdomains = ["9001 discuss.leetcode.com"]
//输出：["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
//解释：例子中仅包含一个网站域名："discuss.leetcode.com"。
//按照前文描述，子域名 "leetcode.com" 和 "com" 都会被访问，所以它们都被访问了 9001 次。
//示例 2：
//
//输入：cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
//输出：["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
//解释：按照前文描述，会访问 "google.mail.com" 900 次，"yahoo.com" 50 次，"intel.mail.com" 1 次，"wiki.org" 5 次。
//而对于父域名，会访问 "mail.com" 900 + 1 = 901 次，"com" 900 + 50 + 1 = 951 次，和 "org" 5 次。
//
//
//提示：
//
//1 <= cpdomain.length <= 100
//1 <= cpdomain[i].length <= 100
//cpdomain[i] 会遵循 "repi d1i.d2i.d3i" 或 "repi d1i.d2i" 格式
//repi 是范围 [1, 104] 内的一个整数
//d1i、d2i 和 d3i 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/subdomain-visit-count
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func subdomainVisits(cpdomains []string) []string {
	var substr func(s string) []string
	substr = func(s string) []string {
		res := []string{s}
		for i := 0; i < len(s); i++ {
			if s[i] == '.' {
				res = append(res, s[i+1:])
			}
		}
		return res
	}
	mp := make(map[string]int)
	for _, domin := range cpdomains {
		split := strings.Split(domin, " ")
		cnt, _ := strconv.Atoi(split[0])
		s := split[1]
		for _, sub := range substr(s) {
			mp[sub] += cnt
		}
	}
	res := make([]string, 0)
	for k, v := range mp {
		res = append(res, fmt.Sprintf("%d %s", v, k))
	}
	return res
}
