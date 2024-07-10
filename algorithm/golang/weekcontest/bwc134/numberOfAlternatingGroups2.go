package bwc134

// 给你一个整数数组 colors 和一个整数 k ，colors表示一个由红色和蓝色瓷砖组成的环，第 i 块瓷砖的颜色为 colors[i] ：
//
//colors[i] == 0 表示第 i 块瓷砖的颜色是 红色 。
//colors[i] == 1 表示第 i 块瓷砖的颜色是 蓝色 。
//环中连续 k 块瓷砖的颜色如果是 交替 颜色（也就是说除了第一块和最后一块瓷砖以外，中间瓷砖的颜色与它 左边 和 右边 的颜色都不同），那么它被称为一个 交替 组。
//
//请你返回 交替 组的数目。
//
//注意 ，由于 colors 表示一个 环 ，第一块 瓷砖和 最后一块 瓷砖是相邻的。
//
//
//
//示例 1：
//
//输入：colors = [0,1,0,1,0], k = 3
//
//输出：3
//
//解释：
//
//
//
//交替组包括：
//
//
//
//示例 2：
//
//输入：colors = [0,1,0,0,1,0,1], k = 6
//
//输出：2
//
//解释：
//
//
//
//交替组包括：
//
//
//
//示例 3：
//
//输入：colors = [1,1,0,1], k = 4
//
//输出：0
//
//解释：
//
//
//
//
//
//提示：
//
//3 <= colors.length <= 105
//0 <= colors[i] <= 1
//3 <= k <= colors.length

func numberOfAlternatingGroups2(colors []int, k int) int {
	n := len(colors)
	// prefixSum0[i][0]为[0,i)的偶数位上的0的个数
	// prefixSum0[i][1]为[0,i)的奇数位上的0的个数
	// 则[i,j)上的偶数位的0的个数为 prefixSum0[j][0]-prefixSum0[i][0]
	// 则[i,j)上的奇数位的0的个数为 prefixSum0[j][1]-prefixSum0[i][1]
	prefixSum0 := make([][]int, 2*n+1)
	prefixSum1 := make([][]int, 2*n+1)
	for i := 0; i <= 2*n; i++ {
		prefixSum0[i] = make([]int, 2)
		prefixSum1[i] = make([]int, 2)
	}
	for i := 0; i < 2*n; i++ {
		prefixSum0[i+1][0] = prefixSum0[i][0]
		prefixSum0[i+1][1] = prefixSum0[i][1]
		prefixSum1[i+1][0] = prefixSum1[i][0]
		prefixSum1[i+1][1] = prefixSum1[i][1]
		if i%2 == 0 {
			if colors[i%n] == 0 {
				prefixSum0[i+1][0]++
			} else {
				prefixSum0[i+1][1]++
			}
		} else {
			if colors[i%n] == 0 {
				prefixSum0[i+1][1]++
			} else {
				prefixSum1[i+1][1]++
			}
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		// 分别是偶数位上的0和1的数量，奇数位上的0和1的数量
		c0 := prefixSum0[i+k][0] - prefixSum0[i][0]
		c1 := prefixSum1[i+k][0] - prefixSum1[i][0]
		c2 := prefixSum0[i+k][1] - prefixSum0[i][1]
		c3 := prefixSum1[i+k][1] - prefixSum1[i][1]
		if c0 == 0 && c3 == 0 || c1 == 0 && c2 == 0 {
			res++
		}
	}
	return res
}
