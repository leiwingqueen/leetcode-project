package dfs

// 有 n 位乘客即将登机，飞机正好有 n 个座位。第一位乘客的票丢了，他随便选了一个座位坐下。
//
//剩下的乘客将会：
//
//如果他们自己的座位还空着，就坐到自己的座位上，
//
//当他们自己的座位被占用时，随机选择其他座位
//第 n 位乘客坐在自己的座位上的概率是多少？
//
//
//
//示例 1：
//
//输入：n = 1
//输出：1.00000
//解释：第一个人只会坐在自己的位置上。
//示例 2：
//
//输入: n = 2
//输出: 0.50000
//解释：在第一个人选好座位坐下后，第二个人坐在自己的座位上的概率是 0.5。
//
//
//提示：
//
//1 <= n <= 10^5

// 超时
func nthPersonGetsNthSeat(n int) float64 {
	var dfs func(idx int, choose []bool) float64
	dfs = func(idx int, choose []bool) float64 {
		if idx == n-1 {
			if !choose[idx] {
				return 1
			} else {
				return 0
			}
		}
		if choose[n-1] {
			return 0
		}
		if idx > 0 && !choose[idx] {
			choose[idx] = true
			sum := dfs(idx+1, choose)
			choose[idx] = false
			return sum
		} else {
			var sum float64
			for i := 0; i < n; i++ {
				if !choose[i] {
					choose[i] = true
					sum += dfs(idx+1, choose) / float64(n-idx)
					choose[i] = false
				}
			}
			return sum
		}
	}
	return dfs(0, make([]bool, n))
}

func nthPersonGetsNthSeat2(n int) float64 {
	if n == 1 {
		return 1
	} else {
		return 0.5
	}
}
