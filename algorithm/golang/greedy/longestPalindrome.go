package greedy

import "strings"

// 给你一个字符串数组 words 。words 中每个元素都是一个包含 两个 小写英文字母的单词。
//
//请你从 words 中选择一些元素并按 任意顺序 连接它们，并得到一个 尽可能长的回文串 。每个元素 至多 只能使用一次。
//
//请你返回你能得到的最长回文串的 长度 。如果没办法得到任何一个回文串，请你返回 0 。
//
//回文串 指的是从前往后和从后往前读一样的字符串。
//
//
//
//示例 1：
//
//输入：words = ["lc","cl","gg"]
//输出：6
//解释：一个最长的回文串为 "lc" + "gg" + "cl" = "lcggcl" ，长度为 6 。
//"clgglc" 是另一个可以得到的最长回文串。
//示例 2：
//
//输入：words = ["ab","ty","yt","lc","cl","ab"]
//输出：8
//解释：最长回文串是 "ty" + "lc" + "cl" + "yt" = "tylcclyt" ，长度为 8 。
//"lcyttycl" 是另一个可以得到的最长回文串。
//示例 3：
//
//输入：words = ["cc","ll","xx"]
//输出：2
//解释：最长回文串是 "cc" ，长度为 2 。
//"ll" 是另一个可以得到的最长回文串。"xx" 也是。
//
//
//提示：
//
//1 <= words.length <= 105
//words[i].length == 2
//words[i] 仅包含小写英文字母。

func longestPalindrome(words []string) int {
	mp := make(map[string]int)
	for _, word := range words {
		mp[word]++
	}
	res := 0
	for word := range mp {
		expect := string([]byte{word[1], word[0]})
		if expect != word {
			cnt := min(mp[word], mp[expect])
			res += 4 * cnt
			mp[word] -= cnt
			if mp[word] == 0 {
				delete(mp, word)
			}
			mp[expect] -= cnt
			if mp[expect] == 0 {
				delete(mp, expect)
			}
		} else {
			cnt := mp[word] / 2
			res += 4 * cnt
			mp[word] -= cnt * 2
			if mp[word] == 0 {
				delete(mp, word)
			}
		}
	}
	if len(mp) > 0 {
		for word := range mp {
			if word[0] == word[1] {
				res += 2
				break
			}
		}
	}
	return res
}

// 优化写法
func longestPalindrome2(words []string) int {
	freq := make(map[string]int)
	for _, word := range words {
		freq[word]++
	}
	res := 0
	mid := false
	for word := range freq {
		revert := string([]byte{word[1], word[0]})
		if word != revert {
			// 只需要处理一遍
			if strings.Compare(word, revert) > 0 {
				cnt := min(freq[word], freq[revert])
				res += 4 * cnt
			}
		} else {
			cnt := freq[word] / 2
			res += 4 * cnt
			if freq[word]%2 == 1 {
				mid = true
			}
		}
	}
	if mid {
		res += 2
	}
	return res
}
