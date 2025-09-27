package math

import "math"

// 给你一个由 X-Y 平面上的点组成的数组 points ，其中 points[i] = [xi, yi] 。从其中取任意三个不同的点组成三角形，返回能组成的最大三角形的面积。与真实值误差在 10-5 内的答案将会视为正确答案。
//
//
//
//示例 1：
//
//
//输入：points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
//输出：2.00000
//解释：输入中的 5 个点如上图所示，红色的三角形面积最大。
//示例 2：
//
//输入：points = [[1,0],[0,0],[0,1]]
//输出：0.50000
//
//
//提示：
//
//3 <= points.length <= 50
//-50 <= xi, yi <= 50
//给出的所有点 互不相同

func largestTriangleArea(points [][]int) float64 {
	n := len(points)
	calDistance := func(p1, p2 []int) float64 {
		x := float64(p1[0] - p2[0])
		y := float64(p1[1] - p2[1])
		return math.Sqrt(x*x + y*y)
	}
	// 枚举所有的三角形
	res := float64(0)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for k := j + 1; k < n; k++ {
				// 海伦公式计算面积
				a := calDistance(points[i], points[j])
				b := calDistance(points[j], points[k])
				c := calDistance(points[k], points[i])
				if a+b > c && a+c > b && b+c > a {
					p := (a + b + c) / 2
					area := math.Sqrt(p * (p - a) * (p - b) * (p - c))
					res = math.Max(res, area)
				}
			}
		}
	}
	return res
}
