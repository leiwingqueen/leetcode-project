package twopointer

import "strings"

// 一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，"Hello World" ，"HELLO" ，"hello world hello world" 都是句子。每个单词都 只 包含大写和小写英文字母。
//
//如果两个句子 sentence1 和 sentence2 ，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，那么我们称这两个句子是 相似的 。比方说，sentence1 = "Hello my name is Jane" 且 sentence2 = "Hello Jane" ，我们可以往 sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。
//
//给你两个句子 sentence1 和 sentence2 ，如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false 。
//
//
//
//示例 1：
//
//输入：sentence1 = "My name is Haley", sentence2 = "My Haley"
//输出：true
//解释：可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
//示例 2：
//
//输入：sentence1 = "of", sentence2 = "A lot of words"
//输出：false
//解释：没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
//示例 3：
//
//输入：sentence1 = "Eating right now", sentence2 = "Eating"
//输出：true
//解释：可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
//示例 4：
//
//输入：sentence1 = "Luky", sentence2 = "Lucccky"
//输出：false
//
//
//提示：
//
//1 <= sentence1.length, sentence2.length <= 100
//sentence1 和 sentence2 都只包含大小写英文字母和空格。
//sentence1 和 sentence2 中的单词都只由单个空格隔开。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sentence-similarity-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 错误
func areSentencesSimilar(sentence1 string, sentence2 string) bool {
	if len(sentence1) < len(sentence2) {
		sentence1, sentence2 = sentence2, sentence1
	}
	s1 := strings.Split(sentence1, " ")
	s2 := strings.Split(sentence2, " ")
	p1 := 0
	p2 := 0
	cnt := 0
	for p2 < len(s2) {
		if p1 == len(s1) {
			return false
		}
		if s1[p1] != s2[p2] {
			for p1 < len(s1) && s1[p1] != s2[p2] {
				p1++
			}
			cnt++
		}
		if cnt > 1 {
			return false
		}
		if p1 == len(s1) {
			return false
		}
		p1++
		p2++
	}
	if p2 != len(s2) {
		return false
	}
	if p1 == p2 {
		return true
	}
	return p1 == len(s1)
}

// 最大共同前缀、后缀
func areSentencesSimilar2(sentence1 string, sentence2 string) bool {
	if len(sentence1) < len(sentence2) {
		sentence1, sentence2 = sentence2, sentence1
	}
	s1 := strings.Split(sentence1, " ")
	s2 := strings.Split(sentence2, " ")
	l := 0
	for l < len(s2) && s1[l] == s2[l] {
		l++
	}
	if l == len(s2) {
		return true
	}
	r := len(s2) - 1
	r2 := len(s1) - 1
	for r >= l && s1[r2] == s2[r] {
		r--
		r2--
	}
	return l > r
}
