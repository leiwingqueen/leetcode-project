package dp

//汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
//
//沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
//
//假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
//
//当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
//
//为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
//
//注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
//
//
//
//示例 1：
//
//输入：target = 1, startFuel = 1, stations = []
//输出：0
//解释：我们可以在不加油的情况下到达目的地。
//示例 2：
//
//输入：target = 100, startFuel = 1, stations = [[10,100]]
//输出：-1
//解释：我们无法抵达目的地，甚至无法到达第一个加油站。
//示例 3：
//
//输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
//输出：2
//解释：
//我们出发时有 10 升燃料。
//我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
//然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
//并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
//我们沿途在1两个加油站停靠，所以返回 2 。
//
//
//提示：
//
//1 <= target, startFuel, stations[i][1] <= 10^9
//0 <= stations.length <= 500
//0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-number-of-refueling-stops
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//问题转化
//f(n,k)为前n个加油站只加油k次，到第n个加油站剩余的最大油量
//为了简化问题，我们可以认为起始点也是一个加油站，但是油量为0
// f(n,k)=max{f(n-1,k)-d,f(n-1,k-1)>=d?f(n-1,k-1)-d+A[n]:-1}，假设d是距离上一个加油站的距离
//时间复杂度O(n^2)
func minRefuelStops(target int, startFuel int, stations [][]int) int {
	n := len(stations)
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, n+1)
	}
	//初始化
	dp[0][0] = startFuel
	for i := 1; i <= n; i++ {
		dp[i][0] = startFuel - stations[i-1][0]
		if dp[i][0] < 0 {
			dp[i][0] = -1
		}
	}
	//dp迭代
	for i := 1; i <= n; i++ {
		for j := 1; j <= n; j++ {
			dp[i][j] = -1
			//距离上一个加油站的距离
			d := 0
			if i <= 1 {
				d = stations[0][0]
			} else {
				d = stations[i-1][0] - stations[i-2][0]
			}
			//选
			if dp[i-1][j-1] >= d {
				dp[i][j] = dp[i-1][j-1] - d + stations[i-1][1]
			}
			//不选
			if j <= i-1 && dp[i-1][j] >= d && dp[i-1][j]-d >= dp[i][j] {
				dp[i][j] = dp[i-1][j] - d
			}
		}
	}
	//求合法的最小解
	d := target
	if n > 0 {
		d = target - stations[n-1][0]
	}
	for i := 0; i <= n; i++ {
		if dp[n][i] >= d {
			return i
		}
	}
	return -1
}
