package wc372

import "sort"

// You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.
//
//If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].
//
//You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai while Bob is in building bi.
//
//Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.
//
//
//
//Example 1:
//
//Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
//Output: [2,5,-1,5,2]
//Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2].
//In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5].
//In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
//In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
//In the fifth query, Alice and Bob are already in the same building.
//For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
//For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
//Example 2:
//
//Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
//Output: [7,6,-1,4,6]
//Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
//In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
//In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
//In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
//In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
//For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
//For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
//
//
//
//Constraints:
//
//1 <= heights.length <= 5 * 104
//1 <= heights[i] <= 109
//1 <= queries.length <= 5 * 104
//queries[i] = [ai, bi]
//0 <= ai, bi <= heights.length - 1

func leftmostBuildingQueries(heights []int, queries [][]int) []int {
	n := len(heights)
	find := func(query []int) int {
		i, j := query[0], query[1]
		for l := j; l < n; l++ {
			if heights[l] >= heights[i] && heights[l] >= heights[j] {
				return l
			}
		}
		return -1
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		res[i] = find(query)
	}
	return res
}

// 单调栈+二分
func leftmostBuildingQueries2(heights []int, queries [][]int) []int {
	arr := make([][]int, len(queries))
	for i, query := range queries {
		if query[0] > query[1] {
			query[0], query[1] = query[1], query[0]
		}
		arr[i] = []int{i, query[0], query[1]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][2] > arr[j][2]
	})
	res := make([]int, len(arr))
	var stack []int
	for _, q := range arr {
		i, x, y := q[0], q[1], q[2]
		// 单调栈处理
		if len(stack) > 0 && stack[len(stack)-1] != y {
			for len(stack) > 0 && heights[stack[len(stack)-1]] < heights[y] {
				stack = stack[:len(stack)-1]
			}
			stack = append(stack, y)
		}
		if x == y || heights[y] > heights[x] {
			res[i] = y
		} else {
			if len(stack) == 0 || heights[stack[0]] <= heights[x] {
				res[i] = -1
			} else {
				// 二分查找
				idx := sort.Search(len(stack), func(i int) bool {
					return heights[stack[i]] <= heights[x]
				})
				res[i] = stack[idx-1]
			}
		}
	}
	return res
}
