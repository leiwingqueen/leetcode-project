package array

import "sort"

// 给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。
//
//同时给你两个长度为 n 的整数数组 startTime 和 endTime 。它们表示这次活动中 n 个时间 没有重叠 的会议，其中第 i 个会议的时间为 [startTime[i], endTime[i]] 。
//
//你可以重新安排 至多 一个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化 相邻两个会议之间的 最长 连续空余时间。
//
//请你返回重新安排会议以后，可以得到的 最大 空余时间。
//
//注意，会议 不能 安排到整个活动的时间以外，且会议之间需要保持互不重叠。
//
//注意：重新安排会议以后，会议之间的顺序可以发生改变。
//
//
//
//示例 1：
//
//输入：eventTime = 5, startTime = [1,3], endTime = [2,5]
//
//输出：2
//
//解释：
//
//
//
//将 [1, 2] 的会议安排到 [2, 3] ，得到空余时间 [0, 2] 。
//
//示例 2：
//
//输入：eventTime = 10, startTime = [0,7,9], endTime = [1,8,10]
//
//输出：7
//
//解释：
//
//
//
//将 [0, 1] 的会议安排到 [8, 9] ，得到空余时间 [0, 7] 。
//
//示例 3：
//
//输入：eventTime = 10, startTime = [0,3,7,9], endTime = [1,4,8,10]
//
//输出：6
//
//解释：
//
//
//
//将 [3, 4] 的会议安排到 [8, 9] ，得到空余时间 [1, 7] 。
//
//示例 4：
//
//输入：eventTime = 5, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
//
//输出：0
//
//解释：
//
//活动中的所有时间都被会议安排满了。
//
//
//
//提示：
//
//1 <= eventTime <= 109
//n == startTime.length == endTime.length
//2 <= n <= 105
//0 <= startTime[i] < endTime[i] <= eventTime
//endTime[i] <= startTime[i + 1] 其中 i 在范围 [0, n - 2] 之间。

func maxFreeTime(eventTime int, startTime []int, endTime []int) int {
	n := len(startTime)
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = []int{startTime[i], endTime[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][0] < arr[j][0]
	})
	// 先简单考虑平移的场景
	res := 0
	for i := 0; i < n; i++ {
		left := 0
		if i > 0 {
			left = arr[i-1][1]
		}
		right := eventTime
		if i < n-1 {
			right = arr[i+1][0]
		}
		res = max(res, right-left-(arr[i][1]-arr[i][0]))
	}
	// 这里再考虑插入的场景
	rightGapArr := make([]int, n)
	rightGapArr[n-1] = 0
	for i := n - 2; i >= 0; i-- {
		right := eventTime
		if i+2 < n {
			right = arr[i+2][0]
		}
		rightGapArr[i] = max(rightGapArr[i+1], right-arr[i+1][1])
	}
	leftGap := 0
	for i := 0; i < n; i++ {
		gap := arr[i][1] - arr[i][0]
		left := 0
		if i > 0 {
			left = arr[i-1][1]
		}
		if gap <= leftGap || gap <= rightGapArr[i] {
			right := eventTime
			if i < n-1 {
				right = arr[i+1][0]
			}
			res = max(res, right-left)
		}
		leftGap = max(leftGap, arr[i][0]-left)
	}
	return res
}
