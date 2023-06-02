package bwc105

// 给你一个下标从 0 开始的整数数组 nums ，你可以在一些下标之间遍历。对于两个下标 i 和 j（i != j），当且仅当 gcd(nums[i], nums[j]) > 1 时，我们可以在两个下标之间通行，其中 gcd 是两个数的 最大公约数 。
//
//你需要判断 nums 数组中 任意 两个满足 i < j 的下标 i 和 j ，是否存在若干次通行可以从 i 遍历到 j 。
//
//如果任意满足条件的下标对都可以遍历，那么返回 true ，否则返回 false 。
//
//
//
//示例 1：
//
//输入：nums = [2,3,6]
//输出：true
//解释：这个例子中，总共有 3 个下标对：(0, 1) ，(0, 2) 和 (1, 2) 。
//从下标 0 到下标 1 ，我们可以遍历 0 -> 2 -> 1 ，我们可以从下标 0 到 2 是因为 gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1 ，从下标 2 到 1 是因为 gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1 。
//从下标 0 到下标 2 ，我们可以直接遍历，因为 gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1 。同理，我们也可以从下标 1 到 2 因为 gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1 。
//示例 2：
//
//输入：nums = [3,9,5]
//输出：false
//解释：我们没法从下标 0 到 2 ，所以返回 false 。
//示例 3：
//
//输入：nums = [4,3,12,8]
//输出：true
//解释：总共有 6 个下标对：(0, 1) ，(0, 2) ，(0, 3) ，(1, 2) ，(1, 3) 和 (2, 3) 。所有下标对之间都存在可行的遍历，所以返回 true 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 105

func canTraverseAllPairs(nums []int) bool {
	n := len(nums)
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	graph := make([][]int, n)
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if gcd(nums[i], nums[j]) > 1 {
				graph[i] = append(graph[i], j)
				graph[j] = append(graph[j], i)
			}
		}
	}
	visit := make([]bool, n)
	var dfs func(node int) int
	dfs = func(node int) int {
		visit[node] = true
		cnt := 1
		for _, next := range graph[node] {
			if !visit[next] {
				cnt += dfs(next)
			}
		}
		return cnt
	}
	c := dfs(0)
	return c == n
}
