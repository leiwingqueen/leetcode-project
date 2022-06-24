package string

//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
//
// 
//
//示例 1：
//
//输入："hello"
//输出："holle"
//示例 2：
//
//输入："leetcode"
//输出："leotcede"
// 
//
//提示：
//
//元音字母不包含字母 "y" 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func reverseVowels(s string) string {
	mp := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true,
		'A': true, 'E': true, 'I': true, 'O': true, 'U': true}
	l := 0
	r := len(s)-1
	res := []byte(s)
	for l < r {
		for l < r {
			if _, contain := mp[s[l]]; contain {
				break
			}
			l++
		}
		for l < r {
			if _, contain := mp[s[r]]; contain {
				break
			}
			r--
		}
		//swap
		res[l] = s[r]
		res[r] = s[l]
		l++
		r--
	}
	return string(res)
}
