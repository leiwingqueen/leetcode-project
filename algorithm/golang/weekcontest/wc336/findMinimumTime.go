package wc336

import "sort"

// 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
//
//当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
//
//请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
//
//

// 任务调度，常规做法是按结束时间，看完答案后好像理解了。偏贪心算法
func findMinimumTime(tasks [][]int) int {
	sort.Slice(tasks, func(i, j int) bool {
		return tasks[i][1] < tasks[j][1]
	})
	run := make([]bool, 2001)
	res := 0
	for _, task := range tasks {
		start, end, dur := task[0], task[1], task[2]
		for _, r := range run[start : end+1] {
			if dur == 0 {
				break
			}
			if r {
				dur--
			}
		}
		for i := end; i >= start; i-- {
			if dur == 0 {
				break
			}
			if !run[i] {
				run[i] = true
				dur--
				res++
			}
		}
	}
	return res
}
