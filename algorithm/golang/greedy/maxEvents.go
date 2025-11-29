package greedy

import "sort"

// 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
//
//你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。
//
//请你返回你可以参加的 最大 会议数目。
//
//
//
//示例 1：
//
//
//
//输入：events = [[1,2],[2,3],[3,4]]
//输出：3
//解释：你可以参加所有的三个会议。
//安排会议的一种方案如上图。
//第 1 天参加第一个会议。
//第 2 天参加第二个会议。
//第 3 天参加第三个会议。
//示例 2：
//
//输入：events= [[1,2],[2,3],[3,4],[1,2]]
//输出：4
//
//
//提示：​​​​​​
//
//1 <= events.length <= 105
//events[i].length == 2
//1 <= startDayi <= endDayi <= 105

// 贪心算法
// 1. 排序，根据startTime做排序。如果相同的startTime，那么选endTime比较小的放前面
// 2. 按每个event顺序处理，并且记录上一次最后处理lastTime。
// 3. 如果lastTime+1>endTime，那么跳到下一个event
// 4. 如果lastTime+1<=endTime，那么下一个会议时间为max(lastTime+1,startTime)
func maxEvents(events [][]int) int {
	sort.Slice(events, func(i, j int) bool {
		s1, e1 := events[i][0], events[i][1]
		s2, e2 := events[j][0], events[j][1]
		if s1 != s2 {
			return s1 < s2
		} else {
			return e1 < e2
		}
	})
	n := len(events)
	lastTime := 0
	res := 0
	for i := 0; i < n; i++ {
		start, end := events[i][0], events[i][1]
		if lastTime+1 <= end {
			lastTime = max(lastTime+1, start)
			res++
		}
	}
	return res
}

func maxEvents2(events [][]int) int {
	sort.Slice(events, func(i, j int) bool {
		s1, e1 := events[i][0], events[i][1]
		s2, e2 := events[j][0], events[j][1]
		if e1 != e2 {
			return e1 < e2
		} else {
			return s1 < s2
		}
	})
	n := len(events)
	lastTime := 0
	res := 0
	for i := 0; i < n; i++ {
		start, end := events[i][0], events[i][1]
		if lastTime+1 <= end {
			lastTime = max(lastTime+1, start)
			res++
		}
	}
	return res
}
