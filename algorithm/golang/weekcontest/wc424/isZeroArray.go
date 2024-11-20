package wc424

//给定一个长度为 n 的整数数组 nums 和一个二维数组 queries，其中 queries[i] = [li, ri]。
//
//对于每个查询 queries[i]：
//
//在 nums 的下标范围 [li, ri] 内选择一个下标子集。
//将选中的每个下标对应的元素值减 1。
//零数组 是指所有元素都等于 0 的数组。
//
//如果在按顺序处理所有查询后，可以将 nums 转换为 零数组 ，则返回 true，否则返回 false。
//
//数组的 子集 是对数组元素的选择（可能为空）。
//
//
//
//示例 1：
//
//输入： nums = [1,0,1], queries = [[0,2]]
//
//输出： true
//
//解释：
//
//对于 i = 0：
//选择下标子集 [0, 2] 并将这些下标处的值减 1。
//数组将变为 [0, 0, 0]，这是一个零数组。
//示例 2：
//
//输入： nums = [4,3,2,1], queries = [[1,3],[0,2]]
//
//输出： false
//
//解释：
//
//对于 i = 0：
//选择下标子集 [1, 2, 3] 并将这些下标处的值减 1。
//数组将变为 [4, 2, 1, 0]。
//对于 i = 1：
//选择下标子集 [0, 1, 2] 并将这些下标处的值减 1。
//数组将变为 [3, 1, 0, 0]，这不是一个零数组。
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 105
//1 <= queries.length <= 105
//queries[i].length == 2
//0 <= li <= ri < nums.length

// 典型的差分数组的题目
func isZeroArray(nums []int, queries [][]int) bool {
	n := len(nums)
	diff := make([]int, n)
	diff[0] = nums[0]
	for i := 1; i < n; i++ {
		diff[i] = nums[i] - nums[i-1]
	}
	for _, query := range queries {
		l, r := query[0], query[1]
		diff[l]--
		if r < n-1 {
			diff[r+1]++
		}
	}
	// 还原数组
	nums[0] = diff[0]
	if nums[0] > 0 {
		return false
	}
	for i := 1; i < n; i++ {
		nums[i] = nums[i-1] + diff[i]
		if nums[i] > 0 {
			return false
		}
	}
	return true
}
