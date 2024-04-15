package wc393

import "fmt"

// 给你一个字符串 s，表示一个 12 小时制的时间格式，其中一些数字（可能没有）被 "?" 替换。
//
//12 小时制时间格式为 "HH:MM" ，其中 HH 的取值范围为 00 至 11，MM 的取值范围为 00 至 59。最早的时间为 00:00，最晚的时间为 11:59。
//
//你需要将 s 中的 所有 "?" 字符替换为数字，使得结果字符串代表的时间是一个 有效 的 12 小时制时间，并且是可能的 最晚 时间。
//
//返回结果字符串。
//
//
//
//示例 1：
//
//输入： s = "1?:?4"
//
//输出： "11:54"
//
//解释： 通过替换 "?" 字符，可以得到的最晚12小时制时间是 "11:54"。
//
//示例 2：
//
//输入： s = "0?:5?"
//
//输出： "09:59"
//
//解释： 通过替换 "?" 字符，可以得到的最晚12小时制时间是 "09:59"。
//
//
//
//提示：
//
//s.length == 5
//s[2] 是字符 ":"
//除 s[2] 外，其他字符都是数字或 "?"
//输入保证在替换 "?" 字符后至少存在一个介于 "00:00" 和 "11:59" 之间的时间。

// 暴力即可
func findLatestTime(s string) string {
	match := func(hour int, minute int) bool {
		if s[0] != '?' && hour/10 != int(s[0]-'0') {
			return false
		}
		if s[1] != '?' && hour%10 != int(s[1]-'0') {
			return false
		}
		if s[3] != '?' && minute/10 != int(s[3]-'0') {
			return false
		}
		if s[4] != '?' && minute%10 != int(s[4]-'0') {
			return false
		}
		return true
	}
	hour, minute := 0, 0
	for i := 0; i < 12; i++ {
		for j := 0; j < 60; j++ {
			if match(i, j) {
				hour, minute = i, j
			}
		}
	}
	return fmt.Sprintf("%02d:%02d", hour, minute)
}
