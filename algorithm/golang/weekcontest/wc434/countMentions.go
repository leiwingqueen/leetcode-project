package wc434

import (
	"sort"
	"strconv"
	"strings"
)

// 给你一个整数 numberOfUsers 表示用户总数，另有一个大小为 n x 3 的数组 events 。
//
//每个 events[i] 都属于下述两种类型之一：
//
//消息事件（Message Event）：["MESSAGE", "timestampi", "mentions_stringi"]
//事件表示在 timestampi 时，一组用户被消息提及。
//mentions_stringi 字符串包含下述标识符之一：
//id<number>：其中 <number> 是一个区间 [0,numberOfUsers - 1] 内的整数。可以用单个空格分隔 多个 id ，并且 id 可能重复。此外，这种形式可以提及离线用户。
//ALL：提及 所有 用户。
//HERE：提及所有 在线 用户。
//离线事件（Offline Event）：["OFFLINE", "timestampi", "idi"]
//事件表示用户 idi 在 timestampi 时变为离线状态 60 个单位时间。用户会在 timestampi + 60 时自动再次上线。
//返回数组 mentions ，其中 mentions[i] 表示  id 为  i 的用户在所有 MESSAGE 事件中被提及的次数。
//
//最初所有用户都处于在线状态，并且如果某个用户离线或者重新上线，其对应的状态变更将会在所有相同时间发生的消息事件之前进行处理和同步。
//
//注意 在单条消息中，同一个用户可能会被提及多次。每次提及都需要被 分别 统计。
//
//
//
//示例 1：
//
//输入：numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]
//
//输出：[2,2]
//
//解释：
//
//最初，所有用户都在线。
//
//时间戳 10 ，id1 和 id0 被提及，mentions = [1,1]
//
//时间戳 11 ，id0 离线 。
//
//时间戳 71 ，id0 再次 上线 并且 "HERE" 被提及，mentions = [2,2]
//
//示例 2：
//
//输入：numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]
//
//输出：[2,2]
//
//解释：
//
//最初，所有用户都在线。
//
//时间戳 10 ，id1 和 id0 被提及，mentions = [1,1]
//
//时间戳 11 ，id0 离线 。
//
//时间戳 12 ，"ALL" 被提及。这种方式将会包括所有离线用户，所以 id0 和 id1 都被提及，mentions = [2,2]
//
//示例 3：
//
//输入：numberOfUsers = 2, events = [["OFFLINE","10","0"],["MESSAGE","12","HERE"]]
//
//输出：[0,1]
//
//解释：
//
//最初，所有用户都在线。
//
//时间戳 10 ，id0 离线 。
//
//时间戳 12 ，"HERE" 被提及。由于 id0 仍处于离线状态，其将不会被提及，mentions = [0,1]
//
//
//
//提示：
//
//1 <= numberOfUsers <= 100
//1 <= events.length <= 100
//events[i].length == 3
//events[i][0] 的值为 MESSAGE 或 OFFLINE 。
//1 <= int(events[i][1]) <= 105
//在任意 "MESSAGE" 事件中，以 id<number> 形式提及的用户数目介于 1 和 100 之间。
//0 <= <number> <= numberOfUsers - 1
//题目保证 OFFLINE 引用的用户 id 在事件发生时处于 在线 状态。

// 简直就是写业务代码
func countMentions(numberOfUsers int, events [][]string) []int {
	k := numberOfUsers
	sort.Slice(events, func(i, j int) bool {
		e1, e2 := events[i], events[j]
		ts1, _ := strconv.Atoi(e1[1])
		ts2, _ := strconv.Atoi(e2[1])
		if ts1 != ts2 {
			return ts1 < ts2
		} else {
			return e1[0] != e2[0] && e1[0] == "OFFLINE"
		}
	})
	status := make([]bool, k)
	// 下一次上线的时间
	onlineTs := make([]int, k)
	for i := 0; i < k; i++ {
		status[i] = true
	}
	mention := make([]int, k)
	time := 0
	for _, event := range events {
		ts, _ := strconv.Atoi(event[1])
		time = ts
		// 更新状态
		for j := 0; j < k; j++ {
			if !status[j] && onlineTs[j] >= 0 && onlineTs[j] <= time {
				status[j] = true
				onlineTs[j] = 0
			}
		}
		if event[0] == "MESSAGE" {
			str := event[2]
			if str == "ALL" {
				for j := 0; j < k; j++ {
					mention[j]++
				}
			} else if str == "HERE" {
				for j := 0; j < k; j++ {
					if status[j] {
						mention[j]++
					}
				}
			} else {
				users := strings.Split(str, " ")
				for _, user := range users {
					id, _ := strconv.Atoi(user[2:])
					mention[id]++
				}
			}
		} else {
			id, _ := strconv.Atoi(event[2])
			status[id] = false
			onlineTs[id] = ts + 60
		}
	}
	return mention
}
