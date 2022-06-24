package string

//给你一个字符串 S，请你删去其中的所有元音字母（ 'a'，'e'，'i'，'o'，'u'），并返回这个新字符串。
//
// 
//
//示例 1：
//
//输入："leetcodeisacommunityforcoders"
//输出："ltcdscmmntyfrcdrs"
//示例 2：
//
//输入："aeiou"
//输出：""
// 
//
//提示：
//
//S 仅由小写英文字母组成。
//1 <= S.length <= 1000
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-vowels-from-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func removeVowels(s string) string {
	mp := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true,
		'A': true, 'E': true, 'I': true, 'O': true, 'U': true}
	res := make([]byte, 0, len(s))
	for i := 0; i < len(s); i++ {
		if _, contain := mp[s[i]]; !contain {
			res = append(res, s[i])
		}
	}
	return string(res)
}
