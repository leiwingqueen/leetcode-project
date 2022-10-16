package dfs

// 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
//
//给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
//
//
//
//示例 1：
//
//输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
//输出：true
//解释：group1 [1,4], group2 [2,3]
//示例 2：
//
//输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
//输出：false
//示例 3：
//
//输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//输出：false
//
//
//提示：
//
//1 <= n <= 2000
//0 <= dislikes.length <= 104
//dislikes[i].length == 2
//1 <= dislikes[i][j] <= n
//ai < bi
//dislikes 中每一组都 不同
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/possible-bipartition
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func possibleBipartition(n int, dislikes [][]int) bool {
	graph := make([][]int, n)
	for _, arr := range dislikes {
		from := arr[0] - 1
		to := arr[1] - 1
		graph[from] = append(graph[from], to)
		graph[to] = append(graph[to], from)
	}
	colors := make([]int, n)
	var dfs func(node int, c int) bool
	dfs = func(node int, c int) bool {
		if colors[node] != 0 && colors[node] != c {
			return false
		}
		if colors[node] != 0 {
			return true
		}
		colors[node] = c
		for _, to := range graph[node] {
			if c == 1 {
				if !dfs(to, 2) {
					return false
				}
			} else {
				if !dfs(to, 1) {
					return false
				}
			}
		}
		return true
	}
	if !dfs(0, 1) {
		return false
	}
	for i := 1; i < n; i++ {
		if colors[i] == 0 {
			if !dfs(i, 1) {
				return false
			}
		}
	}
	return true
}
