package wc459

// 给你一个二维整数数组 points，其中 points[i] = [xi, yi] 表示第 i 个点在笛卡尔平面上的坐标。
//
//水平梯形 是一种凸四边形，具有 至少一对 水平边（即平行于 x 轴的边）。两条直线平行当且仅当它们的斜率相同。
//
//返回可以从 points 中任意选择四个不同点组成的 水平梯形 数量。
//
//由于答案可能非常大，请返回结果对 109 + 7 取余数后的值。
//
//
//
//示例 1：
//
//输入： points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
//
//输出： 3
//
//解释：
//
//
//
//有三种不同方式选择四个点组成一个水平梯形：
//
//使用点 [1,0]、[2,0]、[3,2] 和 [2,2]。
//使用点 [2,0]、[3,0]、[3,2] 和 [2,2]。
//使用点 [1,0]、[3,0]、[3,2] 和 [2,2]。
//示例 2：
//
//输入： points = [[0,0],[1,0],[0,1],[2,1]]
//
//输出： 1
//
//解释：
//
//
//
//只有一种方式可以组成一个水平梯形。
//
//
//
//提示：
//
//4 <= points.length <= 105
//–108 <= xi, yi <= 108
//所有点两两不同。

// 超时
func countTrapezoids(points [][]int) int {
	mod := 1_000_000_007
	mp := make(map[int]int)
	for _, point := range points {
		y := point[1]
		mp[y]++
	}
	var arr []int
	for _, v := range mp {
		if v >= 2 {
			arr = append(arr, v*(v-1)/2%mod)
		}
	}
	res := 0
	n := len(arr)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			res = (res + arr[i]*arr[j]) % mod
		}
	}
	return res
}

func countTrapezoids2(points [][]int) int {
	mod := 1_000_000_007
	mp := make(map[int]int)
	for _, point := range points {
		y := point[1]
		mp[y]++
	}
	res := 0
	s := 0
	for _, v := range mp {
		if v >= 2 {
			c := v * (v - 1) / 2 % mod
			res = (res + c*s) % mod
			s = (s + c) % mod
		}
	}
	return res
}
