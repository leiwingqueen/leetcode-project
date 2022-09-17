package graph

import "leetcode-go/util"

// 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标， (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
//
//计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
//
//返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
//
//
//
//示例 1：
//
//
//
//输入：rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
//输出：6
//解释：如图所示，三个矩形覆盖了总面积为6的区域。
//从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
//从(1,0)到(2,3)，三个矩形都重叠。
//示例 2：
//
//输入：rectangles = [[0,0,1000000000,1000000000]]
//输出：49
//解释：答案是 1018 对 (109 + 7) 取模的结果， 即 49 。
//
//
//提示：
//
//1 <= rectangles.length <= 200
//rectanges[i].length = 4
//0 <= xi1, yi1, xi2, yi2 <= 109
//矩形叠加覆盖后的总面积不会超越 2^63 - 1 ，这意味着可以用一个 64 位有符号整数来保存面积结果。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/rectangle-area-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func rectangleArea(rectangles [][]int) int {
	n := len(rectangles)
	var check func(rec1 []int, rec2 []int) int64
	check = func(rec1 []int, rec2 []int) int64 {
		x1 := rec1[0]
		y1 := rec1[1]
		x2 := rec1[2]
		y2 := rec1[3]

		x3 := rec2[0]
		y3 := rec2[1]
		x4 := rec2[2]
		y4 := rec2[3]

		if x3 >= x2 || x4 <= x1 || y3 >= y2 || y4 <= y1 {
			return 0
		}
		top := util.Min(x4, x2)
		down := util.Max(x3, x1)
		left := util.Max(y1, y3)
		right := util.Min(y2, y4)
		return int64(top-down) * int64(right-left)
	}
	mod := 1_000_000_007
	var res int64
	for i := 0; i < n; i++ {
		var duplicate int64
		for j := 0; j < i; j++ {
			duplicate += check(rectangles[i], rectangles[j])
		}
		rec := rectangles[i]
		res += int64(rec[2]-rec[0])*int64(rec[3]-rec[1]) - duplicate
	}
	return int(res % int64(mod))
}
