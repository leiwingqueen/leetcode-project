package array

import "sort"

// 给你一个二维整数数组 items ，其中 items[i] = [pricei, beautyi] 分别表示每一个物品的 价格 和 美丽值 。
//
//同时给你一个下标从 0 开始的整数数组 queries 。对于每个查询 queries[j] ，你想求出价格小于等于 queries[j] 的物品中，最大的美丽值 是多少。如果不存在符合条件的物品，那么查询的结果为 0 。
//
//请你返回一个长度与 queries 相同的数组 answer，其中 answer[j]是第 j 个查询的答案。
//
//
//
//示例 1：
//
//输入：items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
//输出：[2,4,5,5,6,6]
//解释：
//- queries[0]=1 ，[1,2] 是唯一价格 <= 1 的物品。所以这个查询的答案为 2 。
//- queries[1]=2 ，符合条件的物品有 [1,2] 和 [2,4] 。
//  它们中的最大美丽值为 4 。
//- queries[2]=3 和 queries[3]=4 ，符合条件的物品都为 [1,2] ，[3,2] ，[2,4] 和 [3,5] 。
//  它们中的最大美丽值为 5 。
//- queries[4]=5 和 queries[5]=6 ，所有物品都符合条件。
//  所以，答案为所有物品中的最大美丽值，为 6 。
//示例 2：
//
//输入：items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
//输出：[4]
//解释：
//每个物品的价格均为 1 ，所以我们选择最大美丽值 4 。
//注意，多个物品可能有相同的价格和美丽值。
//示例 3：
//
//输入：items = [[10,1000]], queries = [5]
//输出：[0]
//解释：
//没有物品的价格小于等于 5 ，所以没有物品可以选择。
//因此，查询的结果为 0 。
//
//
//提示：
//
//1 <= items.length, queries.length <= 105
//items[i].length == 2
//1 <= pricei, beautyi, queries[j] <= 109

// 可以先排除掉不合法的记录，假设一个pricei<=pricej,但是beautyi>=beautyj，那么我们不可能选择j
// 排除掉这些不合法的记录后，我们知道beauty必然也是递增的
// 然后我们只需要使用二分查找即可
func maximumBeauty(items [][]int, queries []int) []int {
	sort.Slice(items, func(i, j int) bool {
		if items[i][0] != items[j][0] {
			return items[i][0] < items[j][0]
		} else {
			return items[i][1] > items[j][1]
		}
	})
	var arr [][]int
	arr = append(arr, items[0])
	for _, item := range items[1:] {
		price, beauty := item[0], item[1]
		pre := arr[len(arr)-1]
		if beauty > pre[1] {
			arr = append(arr, []int{price, beauty})
		}
	}
	k := len(queries)
	res := make([]int, k)
	for i, query := range queries {
		idx := sort.Search(len(arr), func(i int) bool {
			return arr[i][0] > query
		})
		if idx == 0 {
			res[i] = 0
		} else {
			res[i] = arr[idx-1][1]
		}
	}
	return res
}
