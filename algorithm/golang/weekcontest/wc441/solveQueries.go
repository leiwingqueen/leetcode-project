package wc441

// 给你一个 循环 数组 nums 和一个数组 queries 。
//
//对于每个查询 i ，你需要找到以下内容：
//
//数组 nums 中下标 queries[i] 处的元素与 任意 其他下标 j（满足 nums[j] == nums[queries[i]]）之间的 最小 距离。如果不存在这样的下标 j，则该查询的结果为 -1 。
//返回一个数组 answer，其大小与 queries 相同，其中 answer[i] 表示查询i的结果。
//
//
//
//示例 1：
//
//输入： nums = [1,3,1,4,1,3,2], queries = [0,3,5]
//
//输出： [2,-1,3]
//
//解释：
//
//查询 0：下标 queries[0] = 0 处的元素为 nums[0] = 1 。最近的相同值下标为 2，距离为 2。
//查询 1：下标 queries[1] = 3 处的元素为 nums[3] = 4 。不存在其他包含值 4 的下标，因此结果为 -1。
//查询 2：下标 queries[2] = 5 处的元素为 nums[5] = 3 。最近的相同值下标为 1，距离为 3（沿着循环路径：5 -> 6 -> 0 -> 1）。
//示例 2：
//
//输入： nums = [1,2,3,4], queries = [0,1,2,3]
//
//输出： [-1,-1,-1,-1]
//
//解释：
//
//数组 nums 中的每个值都是唯一的，因此没有下标与查询的元素值相同。所有查询的结果均为 -1。
//
//
//
//提示：
//
//1 <= queries.length <= nums.length <= 105
//1 <= nums[i] <= 106
//0 <= queries[i] < nums.length

// 从右到左扫描，可以计算右边距离最近的点
func solveQueries(nums []int, queries []int) []int {
	n := len(nums)
	// 预计算，循环数组其实只需要多计算一段即可
	mp := make(map[int]int)
	for i := n - 1; i >= 0; i-- {
		num := nums[i]
		mp[num] = n + i
	}
	right := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		num := nums[i]
		if v, ok := mp[num]; ok {
			right[i] = v
		} else {
			right[i] = -1
		}
		mp[num] = i
	}
	clear(mp)
	left := make([]int, n)
	for i := 0; i < n; i++ {
		num := nums[i]
		mp[num] = -n + i
	}
	for i := 0; i < n; i++ {
		num := nums[i]
		if v, ok := mp[num]; ok {
			left[i] = v
		} else {
			left[i] = -1
		}
		mp[num] = i
	}
	res := make([]int, len(queries))
	for i, idx := range queries {
		dis := min(right[idx]-idx, idx-left[idx])
		if dis == n {
			res[i] = -1
		} else {
			res[i] = dis
		}
	}
	return res
}
