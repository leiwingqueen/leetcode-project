package greedy

// 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
//
//数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
//
//在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
//
//
//
//示例 1：
//
//输入：nums = [3,5,2,6], k = 2
//输出：[2,6]
//解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
//示例 2：
//
//输入：nums = [2,4,3,3,5,4,9,6], k = 4
//输出：[2,3,3,4]
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//1 <= k <= nums.length

// 单调栈的变形
func mostCompetitive(nums []int, k int) []int {
	n := len(nums)
	p := 0
	var st []int
	var res []int
	// 从[0,r]中选择一个数字
	r := n - k
	for r < n {
		for ; p <= r; p++ {
			for len(st) > 0 && nums[st[len(st)-1]] > nums[p] {
				st = st[:len(st)-1]
			}
			st = append(st, p)
		}
		res = append(res, nums[st[0]])
		st = st[1:]
		// 右边界移动
		r++
	}
	return res
}
