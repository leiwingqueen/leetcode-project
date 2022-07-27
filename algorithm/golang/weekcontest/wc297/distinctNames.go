package wc297

//给你一个字符串数组 ideas 表示在公司命名过程中使用的名字列表。公司命名流程如下：
//
//从 ideas 中选择 2 个 不同 名字，称为 ideaA 和 ideaB 。
//交换 ideaA 和 ideaB 的首字母。
//如果得到的两个新名字 都 不在 ideas 中，那么 ideaA ideaB（串联 ideaA 和 ideaB ，中间用一个空格分隔）是一个有效的公司名字。
//否则，不是一个有效的名字。
//返回 不同 且有效的公司名字的数目。
//
//
//
//示例 1：
//
//输入：ideas = ["coffee","donuts","time","toffee"]
//输出：6
//解释：下面列出一些有效的选择方案：
//- ("coffee", "donuts")：对应的公司名字是 "doffee conuts" 。
//- ("donuts", "coffee")：对应的公司名字是 "conuts doffee" 。
//- ("donuts", "time")：对应的公司名字是 "tonuts dime" 。
//- ("donuts", "toffee")：对应的公司名字是 "tonuts doffee" 。
//- ("time", "donuts")：对应的公司名字是 "dime tonuts" 。
//- ("toffee", "donuts")：对应的公司名字是 "doffee tonuts" 。
//因此，总共有 6 个不同的公司名字。
//
//下面列出一些无效的选择方案：
//- ("coffee", "time")：在原数组中存在交换后形成的名字 "toffee" 。
//- ("time", "toffee")：在原数组中存在交换后形成的两个名字。
//- ("coffee", "toffee")：在原数组中存在交换后形成的两个名字。
//示例 2：
//
//输入：ideas = ["lack","back"]
//输出：0
//解释：不存在有效的选择方案。因此，返回 0 。
//
//
//提示：
//
//2 <= ideas.length <= 5 * 104
//1 <= ideas[i].length <= 10
//ideas[i] 由小写英文字母组成
//ideas 中的所有字符串 互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/naming-a-company
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//暴力解法，超时
func distinctNames(ideas []string) int64 {
	mp1 := make(map[string]struct{})
	for _, d := range ideas {
		mp1[d] = struct{}{}
	}
	var res int64
	mp := make(map[string]struct{})
	for i := 0; i < len(ideas); i++ {
		for j := i + 1; j < len(ideas); j++ {
			s1 := ideas[i]
			s2 := ideas[j]
			s3 := string(s1[0]) + s2[1:]
			s4 := string(s2[0]) + s1[1:]
			_, exist1 := mp1[s3]
			_, exist2 := mp1[s4]
			if !exist1 && !exist2 {
				if _, exist := mp[s3+string(' ')+s4]; !exist {
					res++
				}
				if _, exist := mp[s4+string(' ')+s3]; !exist {
					res++
				}
			}
		}
	}
	return res
}

//map
func distinctNames2(ideas []string) int64 {
	mp := make(map[byte]map[string]struct{})
	for _, s := range ideas {
		if _, exist := mp[s[0]]; !exist {
			mp[s[0]] = make(map[string]struct{})
		}
		v := mp[s[0]]
		v[s[1:]] = struct{}{}
	}
	var res int64
	for i := 0; i < 26; i++ {
		for j := i + 1; j < 26; j++ {
			s1 := mp[byte(i+'a')]
			s2 := mp[byte(j+'a')]
			cnt1 := len(s1)
			cnt2 := len(s2)
			if s1 != nil && s2 != nil {
				for key := range s1 {
					if _, exist := s2[key]; exist {
						cnt1--
						cnt2--
					}
				}
			}
			res += 2 * int64(cnt1) * int64(cnt2)
		}
	}
	return res
}
