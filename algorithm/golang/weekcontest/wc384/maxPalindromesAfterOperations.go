package wc384

import "sort"

// 给你一个下标从 0 开始的字符串数组 words ，数组的长度为 n ，且包含下标从 0 开始的若干字符串。
//
//你可以执行以下操作 任意 次数（包括零次）：
//
//选择整数i、j、x和y，满足0 <= i, j < n，0 <= x < words[i].length，0 <= y < words[j].length，交换 字符 words[i][x] 和 words[j][y] 。
//返回一个整数，表示在执行一些操作后，words 中可以包含的回文字符串的 最大 数量。
//
//注意：在操作过程中，i 和 j 可以相等。
//
//
//
//示例 1：
//
//输入：words = ["abbb","ba","aa"]
//输出：3
//解释：在这个例子中，获得最多回文字符串的一种方式是：
//选择 i = 0, j = 1, x = 0, y = 0，交换 words[0][0] 和 words[1][0] 。words 变成了 ["bbbb","aa","aa"] 。
//words 中的所有字符串都是回文。
//因此，可实现的回文字符串的最大数量是 3 。
//示例 2：
//
//输入：words = ["abc","ab"]
//输出：2
//解释：在这个例子中，获得最多回文字符串的一种方式是：
//选择 i = 0, j = 1, x = 1, y = 0，交换 words[0][1] 和 words[1][0] 。words 变成了 ["aac","bb"] 。
//选择 i = 0, j = 0, x = 1, y = 2，交换 words[0][1] 和 words[0][2] 。words 变成了 ["aca","bb"] 。
//两个字符串都是回文 。
//因此，可实现的回文字符串的最大数量是 2。
//示例 3：
//
//输入：words = ["cd","ef","a"]
//输出：1
//解释：在这个例子中，没有必要执行任何操作。
//words 中有一个回文 "a" 。
//可以证明，在执行任何次数操作后，无法得到更多回文。
//因此，答案是 1 。
//
//
//提示：
//
//1 <= words.length <= 1000
//1 <= words[i].length <= 100
//words[i] 仅由小写英文字母组成。

func maxPalindromesAfterOperations(words []string) int {
	n := len(words)
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	mp := make(map[byte]int)
	arr := make([]int, n)
	for i, word := range words {
		arr[i] = len(words)
		for _, ch := range word {
			mp[(byte)(ch)]++
		}
	}
	sort.Ints(arr)
	res := 0
	for _, l := range arr {
		if l == 1 {
			res++
		} else {
			for l > 1 {
				for _, v := range mp {
					p := min(v/2, l/2)
					l -= 2 * p
				}
			}
			if l <= 1 {
				res++
			}
		}
	}
	return res
}
