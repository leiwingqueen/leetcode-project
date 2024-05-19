package wc398

// 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
//
//周洋哥有一个整数数组 nums 和一个二维整数矩阵 queries，对于 queries[i] = [fromi, toi]，请你帮助周洋哥检查子数组 nums[fromi..toi] 是不是一个 特殊数组 。
//
//返回布尔数组 answer，如果 nums[fromi..toi] 是特殊数组，则 answer[i] 为 true ，否则，answer[i] 为 false 。
//
//
//
//示例 1：
//
//输入：nums = [3,4,1,2,6], queries = [[0,4]]
//
//输出：[false]
//
//解释：
//
//子数组是 [3,4,1,2,6]。2 和 6 都是偶数。
//
//示例 2：
//
//输入：nums = [4,3,1,6], queries = [[0,2],[2,3]]
//
//输出：[false,true]
//
//解释：
//
//子数组是 [4,3,1]。3 和 1 都是奇数。因此这个查询的答案是 false。
//子数组是 [1,6]。只有一对：(1,6)，且包含了奇偶性不同的数字。因此这个查询的答案是 true。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//1 <= queries.length <= 105
//queries[i].length == 2
//0 <= queries[i][0] <= queries[i][1] <= nums.length - 1

func isArraySpecial2(nums []int, queries [][]int) []bool {
	check := func(l, r int) bool {
		if r-l+1 == 1 {
			return true
		}
		for i := l + 1; i <= r; i++ {
			if nums[i-1]%2 == nums[i]%2 {
				return false
			}
		}
		return true
	}
	res := make([]bool, len(queries))
	for i, query := range queries {
		res[i] = check(query[0], query[1])
	}
	return res
}

// 1,0,1,1,1,0,1
func isArraySpecial3(nums []int, queries [][]int) []bool {
	check := func(l, r int) bool {
		if r-l+1 == 1 {
			return true
		}
		for i := l + 1; i <= r; i++ {
			if nums[i-1]%2 == nums[i]%2 {
				return false
			}
		}
		return true
	}
	res := make([]bool, len(queries))
	for i, query := range queries {
		res[i] = check(query[0], query[1])
	}
	return res
}
