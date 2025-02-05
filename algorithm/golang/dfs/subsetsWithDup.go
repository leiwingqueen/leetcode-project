package dfs

// 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的
//子集
//（幂集）。
//
//解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//
//
//示例 1：
//
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
//示例 2：
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//提示：
//
//1 <= nums.length <= 10
//-10 <= nums[i] <= 10

// 完全背包问题
func subsetsWithDup(nums []int) [][]int {
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	var arr []int
	for num := range mp {
		arr = append(arr, num)
	}
	var res [][]int
	n := len(arr)
	var dfs func(idx int, path []int, size int)
	dfs = func(idx int, path []int, size int) {
		if idx == n {
			tmp := make([]int, size)
			copy(tmp, path)
			res = append(res, tmp)
			return
		}
		num := arr[idx]
		for i := 0; i <= mp[num]; i++ {
			if i > 0 {
				path[size+i-1] = num
			}
			dfs(idx+1, path, size+i)
		}
	}
	dfs(0, make([]int, len(nums)), 0)
	return res
}
