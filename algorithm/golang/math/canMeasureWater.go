package math

// 有两个水壶，容量分别为 jug1Capacity 和 jug2Capacity 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到 targetCapacity 升。
//
//如果可以得到 targetCapacity 升水，最后请用以上水壶中的一或两个来盛放取得的 targetCapacity 升水。
//
//你可以：
//
//装满任意一个水壶
//清空任意一个水壶
//从一个水壶向另外一个水壶倒水，直到装满或者倒空
//
//
//示例 1:
//
//输入: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
//输出: true
//解释：来自著名的 "Die Hard"
//示例 2:
//
//输入: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
//输出: false
//示例 3:
//
//输入: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
//输出: true
//
//
//提示:
//
//1 <= jug1Capacity, jug2Capacity, targetCapacity <= 106

// 先尝试DFS
func canMeasureWater(jug1Capacity int, jug2Capacity int, targetCapacity int) bool {
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	j1, j2, target := jug1Capacity, jug2Capacity, targetCapacity
	if j1+j2 < target {
		return false
	}
	if j1+j2 == target || target == 0 {
		return true
	}
	hash := func(x, y int) int64 {
		return int64(x)*int64(1_000_001) + int64(y)
	}
	mem := make(map[int64]bool)
	var dfs func(x, y int) bool
	dfs = func(x, y int) bool {
		// fmt.Println(fmt.Sprintf("%d,%d", x, y))
		if x+y == target {
			return true
		}
		mem[hash(x, y)] = true
		// x,y清空
		if !mem[hash(x, 0)] && dfs(x, 0) {
			return true
		}
		if !mem[hash(0, y)] && dfs(0, y) {
			return true
		}
		// x,y分别填满
		if !mem[hash(x, j2)] && dfs(x, j2) {
			return true
		}
		if !mem[hash(j1, y)] && dfs(j1, y) {
			return true
		}
		// x->y
		k := min(j2-y, x)
		if !mem[hash(x-k, y+k)] && dfs(x-k, y+k) {
			return true
		}
		// y->x
		k = min(j1-x, y)
		if !mem[hash(x+k, y-k)] && dfs(x+k, y-k) {
			return true
		}
		return false
	}
	return dfs(0, 0)
}
