package wc515

import "math"

// 给你一个二维整数数组 drones，其中 drones[i] = [xi, yi, rangei] 表示第 ith 架无人机的横坐标、纵坐标和飞行范围。
//
//另给你一个整数数组 target = [tx, ty]，表示目标的坐标。
//
//如果无人机 drones[i] 的坐标与目标坐标之间的曼哈顿距离小于或等于其 rangei，则该无人机能够到达目标。
//
//返回能够到达目标且与目标之间曼哈顿距离最小的无人机的下标。如果存在多个符合条件的无人机，则返回其中最小的下标。如果没有无人机能够到达目标，则返回 -1。
//
//两个坐标 (xi, yi) 和 (xj, yj) 之间的曼哈顿距离为 |xi - xj| + |yi - yj|。
//
//
//
//示例 1：
//
//输入： drones = [[0,0,8],[2,2,9]], target = [3,4]
//
//输出： 1
//
//解释：
//
//drones[0] 与 target 之间的距离为 |0 - 3| + |0 - 4| = 7，没有超出其飞行范围 8。
//drones[1] 与 target 之间的距离为 |2 - 3| + |2 - 4| = 3，没有超出其飞行范围 9。
//由于 drones[1] 是距离目标最近的无人机，因此答案为 1。
//示例 2：
//
//输入： drones = [[2,1,5],[4,4,5],[6,6,8]], target = [5,5]
//
//输出： 1
//
//解释：
//
//drones[0] 与 target 之间的距离为 |2 - 5| + |1 - 5| = 7，大于其飞行范围 5。
//drones[1] 与 target 之间的距离为 |4 - 5| + |4 - 5| = 2，没有超出其飞行范围 5。
//drones[2] 与 target 之间的距离为 |6 - 5| + |6 - 5| = 2，没有超出其飞行范围 8。
//drones[1] 和 drones[2] 都是距离目标最近的无人机。由于需要返回最小下标，因此答案为 1。
//示例 3：
//
//输入： drones = [[4,4,5]], target = [8,6]
//
//输出： -1
//
//解释：
//
//drones[0] 与 target 之间的距离为 |4 - 8| + |4 - 6| = 6，大于其飞行范围 5。
//没有无人机能够到达目标，因此答案为 -1。
//
//
//提示：
//
//1 <= drones.length <= 100
//drones[i] = [xi, yi, rangei]
//target = [tx, ty]
//-25 <= xi, yi, tx, ty <= 25
//1 <= rangei <= 100

func nearestDrone(drones [][]int, target []int) int {
	abs := func(num int) int {
		if num >= 0 {
			return num
		} else {
			return -num
		}
	}
	distance := func(pos []int) (int, bool) {
		d := abs(pos[0]-target[0]) + abs(pos[1]-target[1])
		if d <= pos[2] {
			return d, true
		} else {
			return 0, false
		}
	}
	minDis := math.MaxInt
	idx := -1
	for i, pos := range drones {
		d, reach := distance(pos)
		if reach && d < minDis {
			minDis = d
			idx = i
		}
	}
	return idx
}
