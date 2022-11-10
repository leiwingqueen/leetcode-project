package dp

// 给定一个二维网格 grid ，其中：
//
//'.' 代表一个空房间
//'#' 代表一堵
//'@' 是起点
//小写字母代表钥匙
//大写字母代表锁
//我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
//
//假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
//
//返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
//
//
//
//示例 1：
//
//
//
//输入：grid = ["@.a.#","###.#","b.A.B"]
//输出：8
//解释：目标是获得所有钥匙，而不是打开所有锁。
//示例 2：
//
//
//
//输入：grid = ["@..aA","..B#.","....b"]
//输出：6
//示例 3:
//
//
//输入: grid = ["@Aa"]
//输出: -1
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 30
//grid[i][j] 只含有 '.', '#', '@', 'a'-'f' 以及 'A'-'F'
//钥匙的数目范围是 [1, 6]
//每个钥匙都对应一个 不同 的字母
//每个钥匙正好打开一个对应的锁
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shortest-path-to-get-all-keys
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func shortestPathAllKeys(grid []string) int {
	m := len(grid)
	n := len(grid[0])
	k := 0
	var start []int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] >= 'a' && grid[i][j] <= 'z' {
				k++
			} else if grid[i][j] == '@' {
				start = []int{i, j}
			}
		}
	}
	l := 1 << k
	dp := make([][][]int, l)
	for i := 0; i < l; i++ {
		dp[i] = make([][]int, m)
		for j := 0; j < m; j++ {
			dp[i][j] = make([]int, n)
		}
	}
	// 初始化
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			dp[0][i][j] = -1
		}
	}
	dp[0][start[0]][start[1]] = 0
	queue := [][]int{start}
	visit := make([][]bool, m)
	for i := 0; i < m; i++ {
		visit[i] = make([]bool, n)
	}
	visit[start[0]][start[1]] = true
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, 1},
		{0, -1},
	}
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			pos := queue[i]
			for _, dir := range dirs {
				x := pos[0] + dir[0]
				y := pos[1] + dir[1]
				if x >= 0 && x < m && y >= 0 && y < n && !visit[x][y] && grid[x][y] == '.' {
					dp[0][x][y] = depth + 1
					queue = append(queue, []int{x, y})
					visit[x][y] = true
				}
			}
		}
		queue = queue[size:]
		depth++
	}
	// dp遍历
	// TODO:状态转换的逻辑还是没想清楚
	return 0
}
