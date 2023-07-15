package array

// 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
//
//0 <= a, b, c, d < n
//a、b、c 和 d 互不相同
//nums[a] + nums[b] + nums[c] + nums[d] == target
//你可以按 任意顺序 返回答案 。
//
//
//
//示例 1：
//
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//示例 2：
//
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
//
//
//提示：
//
//1 <= nums.length <= 200
//-109 <= nums[i] <= 109
//-109 <= target <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/4sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 回溯+记忆
func fourSum(nums []int, target int) [][]int {
	n := len(nums)
	mem := make([][]map[int][][]int, 5)
	for i := 0; i < 5; i++ {
		mem[i] = make([]map[int][][]int, n+1)
		for j := 0; j <= n; j++ {
			mem[i][j] = make(map[int][][]int)
		}
	}
	// 前j个数字的i个数之和为k
	var dfs func(i, j, k int) [][]int
	dfs = func(i, j, k int) [][]int {
		if j < i {
			return [][]int{}
		}
		if i == 0 {
			if k == 0 {
				return [][]int{{}}
			} else {
				return [][]int{}
			}
		}
		if v, exist := mem[i][j][k]; exist {
			return v
		}
		// 不选择
		r1 := dfs(i, j-1, k)
		// 选择
		r2 := dfs(i-1, j-1, k-nums[j-1])
		for _, l := range r2 {
			r1 = append(r1, append(l, nums[j-1]))
		}
		mem[i][j][k] = r1
		return r1
	}
	return dfs(4, n, target)
}

// 需要去重
func fourSum2(nums []int, target int) [][]int {
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
	}
	arr := make([][]int, len(mp))
	idx := 0
	for k, v := range mp {
		arr[idx] = []int{k, v}
		idx++
	}
	n := len(arr)
	mem := make([][]map[int][][]int, 5)
	for i := 0; i < 5; i++ {
		mem[i] = make([]map[int][][]int, n+1)
		for j := 0; j <= n; j++ {
			mem[i][j] = make(map[int][][]int)
		}
	}
	// 前j个数字的i个数之和为k
	var dfs func(i, j, k int) [][]int
	dfs = func(i, j, k int) [][]int {
		if i == 0 {
			if k == 0 {
				return [][]int{{}}
			} else {
				return [][]int{}
			}
		}
		if j == 0 {
			return [][]int{}
		}
		if v, exist := mem[i][j][k]; exist {
			return v
		}
		// 不选择
		r1 := dfs(i, j-1, k)
		// 选择
		for t := 1; t <= arr[j-1][1]; t++ {
			r2 := dfs(i-1, j-1, k-t*arr[j-1][0])
			for _, l := range r2 {
				for m := 0; m < t; m++ {
					l = append(l, arr[j-1][0])
				}
				r1 = append(r1, l)
			}
		}
		mem[i][j][k] = r1
		return r1
	}
	return dfs(4, n, target)
}
