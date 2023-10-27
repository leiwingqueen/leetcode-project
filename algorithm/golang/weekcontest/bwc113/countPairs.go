package bwc113

// 给你一个 二维 整数数组 coordinates 和一个整数 k ，其中 coordinates[i] = [xi, yi] 是第 i 个点在二维平面里的坐标。
//
//我们定义两个点 (x1, y1) 和 (x2, y2) 的 距离 为 (x1 XOR x2) + (y1 XOR y2) ，XOR 指的是按位异或运算。
//
//请你返回满足 i < j 且点 i 和点 j之间距离为 k 的点对数目。
//
//
//
//示例 1：
//
//输入：coordinates = [[1,2],[4,2],[1,3],[5,2]], k = 5
//输出：2
//解释：以下点对距离为 k ：
//- (0, 1)：(1 XOR 4) + (2 XOR 2) = 5 。
//- (2, 3)：(1 XOR 5) + (3 XOR 2) = 5 。
//示例 2：
//
//输入：coordinates = [[1,3],[1,3],[1,3],[1,3],[1,3]], k = 0
//输出：10
//解释：任何两个点之间的距离都为 0 ，所以总共有 10 组点对。
//
//
//提示：
//
//2 <= coordinates.length <= 50000
//0 <= xi, yi <= 106
//0 <= k <= 100

// 先来个暴力，超时
func countPairs(coordinates [][]int, k int) int {
	n := len(coordinates)
	dis := func(c1, c2 []int) int {
		return (c1[0] ^ c2[0]) + (c1[1] ^ c2[1])
	}
	res := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			d := dis(coordinates[i], coordinates[j])
			if d == k {
				res++
			}
		}
	}
	return res
}

func countPairs2(coordinates [][]int, k int) int {
	n := len(coordinates)
	res := 0
	mp := make(map[int64]int)
	for i := 0; i < n; i++ {
		x2, y2 := coordinates[i][0], coordinates[i][1]
		for j := 0; j <= k; j++ {
			x1, y1 := x2^j, y2^(k-j)
			res += mp[(int64(x1)<<32)|int64(y1)]
		}
		mp[(int64(x2)<<32)|int64(y2)]++
	}
	return res
}
