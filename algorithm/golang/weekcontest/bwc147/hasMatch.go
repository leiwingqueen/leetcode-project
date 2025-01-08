package bwc147

import "strings"

// 给你一个字符串 s 和一个模式字符串 p ，其中 p 恰好 包含 一个 '*' 符号。
//
//p 中的 '*' 符号可以被替换为零个或多个字符组成的任意字符序列。
//
//如果 p 可以变成 s 的
//子字符串
//，那么返回 true ，否则返回 false 。
//
//
//
//示例 1：
//
//输入：s = "leetcode", p = "ee*e"
//
//输出：true
//
//解释：
//
//将 '*' 替换为 "tcod" ，子字符串 "eetcode" 匹配模式串。
//
//示例 2：
//
//输入：s = "car", p = "c*v"
//
//输出：false
//
//解释：
//
//不存在匹配模式串的子字符串。
//
//示例 3：
//
//输入：s = "luck", p = "u*"
//
//输出：true
//
//解释：
//
//子字符串 "u" ，"uc" 和 "uck" 都匹配模式串。
//
//
//
//提示：
//
//1 <= s.length <= 50
//1 <= p.length <= 50
//s 只包含小写英文字母。
//p 只包含小写英文字母和一个 '*' 符号。

func hasMatch(s string, p string) bool {
	sp := strings.Split(p, "*")
	head, tail := sp[0], sp[1]
	if head == "" {
		return strings.Contains(s, tail)
	} else if tail == "" {
		return strings.Contains(s, head)
	} else {
		id1 := strings.Index(s, head)
		id2 := strings.LastIndex(s, tail)
		return id1 >= 0 && id2 >= 0 && id1+len(head) <= id2
	}
}
