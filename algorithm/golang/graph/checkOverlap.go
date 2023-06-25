package graph

// 给你一个以 (radius, xCenter, yCenter) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2) ，其中 (x1, y1) 是矩形左下角的坐标，而 (x2, y2) 是右上角的坐标。
//
//如果圆和矩形有重叠的部分，请你返回 true ，否则返回 false 。
//
//换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
//
//
//
//示例 1 ：
//
//
//输入：radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
//输出：true
//解释：圆和矩形存在公共点 (1,0) 。
//示例 2 ：
//
//输入：radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
//输出：false
//示例 3 ：
//
//
//输入：radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
//输出：true
//
//
//提示：
//
//1 <= radius <= 2000
//-104 <= xCenter, yCenter <= 104
//-104 <= x1 < x2 <= 104
//-104 <= y1 < y2 <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/circle-and-rectangle-overlapping
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func checkOverlap(radius int, xCenter int, yCenter int, x1 int, y1 int, x2 int, y2 int) bool {
	l, r := yCenter-radius, yCenter+radius
	d, u := xCenter-radius, xCenter+radius
	if x2 < d || x1 > u || r < y1 || l > y2 {
		return false
	}
	// 分别计算到4条边的最短距离的点
	// (xCenter,y1),(xCenter,y2),(x1,yCenter),(x2,yCenter)
	check := func(x, y int) bool {
		return !(x < x1 || x > x2 || y < y1 || y > y2)
	}
	if check(xCenter, y1) || check(xCenter, y2) || check(x1, yCenter) || check(x2, yCenter) {
		return true
	}
	// 计算4个顶点到圆心的距离
	// (x1,y1),(x2,y1),(x2,y2),(x1,y2)
	check2 := func(x, y int) bool {
		dis := (x-xCenter)*(x-xCenter) + (y-yCenter)*(y-yCenter)
		return dis <= radius*radius
	}
	if check2(x1, y1) || check2(x2, y1) || check2(x2, y2) || check2(x1, y2) {
		return true
	}
	return false
}
