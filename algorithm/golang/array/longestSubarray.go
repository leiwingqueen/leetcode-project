package array

// 给你一个二进制数组 nums ，你需要从中删掉一个元素。
//
// 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
//
// 如果不存在这样的子数组，请返回 0 。
//
// 提示 1：
//
// 输入：nums = [1,1,0,1]
// 输出：3
// 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
// 示例 2：
//
// 输入：nums = [0,1,1,1,0,1,1,0,1]
// 输出：5
// 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
// 示例 3：
//
// 输入：nums = [1,1,1]
// 输出：2
// 解释：你必须要删除一个元素。
//
// 提示：
//
// 1 <= nums.length <= 105
// nums[i] 要么是 0 要么是 1 。

// 分场景讨论：
// 假如全是1，那么直接范围长度-1
// 然后我们尝试把数组整改为tuple(num,cnt)的格式
// 接下来只需要尝试把每个0段的变成1进行统计
func longestSubarray(nums []int) int {
	n := len(nums)
	l, r := 0, 0
	// 构造成二维数组的格式
	var arr [][]int
	for r < n {
		if l == r || nums[l] == nums[r] {
			r++
		} else {
			arr = append(arr, []int{nums[l], r - l})
			l = r
		}
	}
	arr = append(arr, []int{nums[l], r - l})
	// 特殊情况，全0或者全1
	if len(arr) == 1 {
		if arr[0][0] == 0 {
			return 0
		} else {
			return n - 1
		}
	}
	// 其它情况，一个个0的段尝试
	res := 0
	for i, seg := range arr {
		if seg[0] == 1 {
			res = max(res, seg[1])
		} else {
			if seg[1] == 1 && i > 0 && i < len(arr)-1 {
				// 可以尝试把前后两段连接起来
				res = max(res, arr[i-1][1]+arr[i+1][1])
			}
		}
	}
	return res
}
