package topological

//给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，数组 relations 中， relations[i] = [xi, yi] 表示一个先
//修课的关系，也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。
//
// 在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。
//
// 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
//
//
//
// 示例 1：
//
//
//
//
//输入：n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
//输出：3
//解释：上图展示了题目输入的图。在第一个学期中，我们可以上课程 2 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。
//
//
// 示例 2：
//
//
//
//
//输入：n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
//输出：4
//解释：上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。
//
//
// 示例 3：
//
//
//输入：n = 11, relations = [], k = 2
//输出：6
//
//
//
//
// 提示：
//
//
// 1 <= n <= 15
// 1 <= k <= n
// 0 <= relations.length <= n * (n-1) / 2
// relations[i].length == 2
// 1 <= xi, yi <= n
// xi != yi
// 所有先修关系都是不同的，也就是说 relations[i] != relations[j] 。
// 题目输入的图是个有向无环图。
//
//
// Related Topics 位运算 图 动态规划 状态压缩 👍 139 👎 0

// 超时
func minNumberOfSemesters(n int, relations [][]int, k int) int {
	graph := make([][]int, n)
	degree := make([]int, n)
	for _, r := range relations {
		from, to := r[0]-1, r[1]-1
		degree[to]++
		graph[from] = append(graph[from], to)
	}
	var r []int
	var choose func(n int, path int, idx int, c int)
	choose = func(n int, path int, idx int, c int) {
		if idx == n || c == 0 {
			r = append(r, path)
			return
		}
		// 选择
		path |= 1 << idx
		choose(n, path, idx+1, c-1)
		if n-idx > c {
			// 不选
			path -= 1 << idx
			choose(n, path, idx+1, c)
		}
	}
	visit := make([]bool, n)
	update := func(arr []int, mask int, flag bool) {
		for i := 0; i < len(arr); i++ {
			if mask&(1<<i) != 0 {
				for _, next := range graph[arr[i]] {
					if flag {
						degree[next]--
					} else {
						degree[next]++
					}
				}
				visit[arr[i]] = flag
			}
		}
	}
	var dfs func(cnt int) int
	dfs = func(cnt int) int {
		if cnt <= 0 {
			return 0
		}
		var arr []int
		for i, d := range degree {
			if d == 0 && !visit[i] {
				arr = append(arr, i)
			}
		}
		c := len(arr)
		if k < c {
			c = k
		}
		r = []int{}
		choose(len(arr), 0, 0, k)
		res := cnt
		for _, mask := range r {
			update(arr, mask, true)
			sub := dfs(cnt-c) + 1
			update(arr, mask, false)
			if sub < res {
				res = sub
			}
		}
		return res
	}
	return dfs(n)
}

// 增加贪心，还是错误
func minNumberOfSemesters2(n int, relations [][]int, k int) int {
	graph := make([][]int, n)
	degree := make([]int, n)
	for _, r := range relations {
		from, to := r[0]-1, r[1]-1
		degree[to]++
		graph[from] = append(graph[from], to)
	}
	var r []int
	var choose func(n int, path int, idx int, c int)
	choose = func(n int, path int, idx int, c int) {
		if idx == n || c == 0 {
			r = append(r, path)
			return
		}
		// 选择
		path |= 1 << idx
		choose(n, path, idx+1, c-1)
		if n-idx > c {
			// 不选
			path -= 1 << idx
			choose(n, path, idx+1, c)
		}
	}
	visit := make([]bool, n)
	update := func(arr []int, mask int, flag bool) {
		for i := 0; i < len(arr); i++ {
			if mask&(1<<i) != 0 {
				for _, next := range graph[arr[i]] {
					if flag {
						degree[next]--
					} else {
						degree[next]++
					}
				}
				visit[arr[i]] = flag
			}
		}
	}
	var dfs func(cnt int) int
	dfs = func(cnt int) int {
		if cnt <= 0 {
			return 0
		}
		var arr []int
		for i, d := range degree {
			if d == 0 && !visit[i] {
				arr = append(arr, i)
			}
		}
		update(arr, (1<<len(arr))-1, true)
		return dfs(cnt-len(arr)) + (len(arr)+k-1)/k
	}
	return dfs(n)
}

// 增加记忆总算过了
func minNumberOfSemesters3(n int, relations [][]int, k int) int {
	graph := make([][]int, n)
	degree := make([]int, n)
	for _, r := range relations {
		from, to := r[0]-1, r[1]-1
		degree[to]++
		graph[from] = append(graph[from], to)
	}
	var r []int
	var choose func(n int, path int, idx int, c int)
	choose = func(n int, path int, idx int, c int) {
		if idx == n || c == 0 {
			r = append(r, path)
			return
		}
		// 选择
		path |= 1 << idx
		choose(n, path, idx+1, c-1)
		if n-idx > c {
			// 不选
			path -= 1 << idx
			choose(n, path, idx+1, c)
		}
	}
	// visit := make([]bool, n)
	visit := 0
	update := func(arr []int, mask int, flag bool) {
		for i := 0; i < len(arr); i++ {
			if mask&(1<<i) != 0 {
				for _, next := range graph[arr[i]] {
					if flag {
						degree[next]--
					} else {
						degree[next]++
					}
				}
				if flag {
					visit |= 1 << arr[i]
				} else {
					visit ^= 1 << arr[i]
				}
			}
		}
	}
	mem := make(map[int]int)
	var dfs func(cnt int) int
	dfs = func(cnt int) int {
		if cnt <= 0 {
			return 0
		}
		if v, exist := mem[visit]; exist {
			return v
		}
		var arr []int
		for i, d := range degree {
			if d == 0 && visit&(1<<i) == 0 {
				arr = append(arr, i)
			}
		}
		c := len(arr)
		if k < c {
			c = k
		}
		r = []int{}
		choose(len(arr), 0, 0, k)
		res := cnt
		for _, mask := range r {
			update(arr, mask, true)
			sub := dfs(cnt-c) + 1
			update(arr, mask, false)
			if sub < res {
				res = sub
			}
		}
		mem[visit] = res
		return res
	}
	return dfs(n)
}
