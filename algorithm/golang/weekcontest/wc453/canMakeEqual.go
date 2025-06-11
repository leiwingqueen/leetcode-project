package wc453

// 给你一个大小为 n 的整数数组 nums，其中只包含 1 和 -1，以及一个整数 k。
//
//你可以最多进行 k 次以下操作：
//
//选择一个下标 i（0 <= i < n - 1），然后将 nums[i] 和 nums[i + 1] 同时 乘以 -1。
//
//注意：你可以在 不同 的操作中多次选择相同的下标 i。
//
//如果在最多 k 次操作后可以使数组的所有元素相等，则返回 true；否则，返回 false。
//
//
//
//示例 1：
//
//输入： nums = [1,-1,1,-1,1], k = 3
//
//输出： true
//
//解释：
//
//我们可以通过以下两次操作使数组的所有元素相等：
//
//选择下标 i = 1，将 nums[1] 和 nums[2] 同时乘以 -1。此时 nums = [1,1,-1,-1,1]。
//选择下标 i = 2，将 nums[2] 和 nums[3] 同时乘以 -1。此时 nums = [1,1,1,1,1]。
//示例 2：
//
//输入： nums = [-1,-1,-1,1,1,1], k = 5
//
//输出： false
//
//解释：
//
//在最多 5 次操作内，无法使数组的所有元素相等。
//
//
//
//提示：
//
//1 <= n == nums.length <= 105
//nums[i] 的值为 -1 或 1。
//1 <= k <= n

// 贪心
func canMakeEqual(nums []int, k int) bool {
	n := len(nums)
	check := func(expect int) bool {
		arr := make([]int, n)
		copy(arr, nums)
		t := k
		for i := n - 2; i >= 0; i-- {
			if arr[i+1] != expect {
				if t == 0 {
					return false
				}
				t--
				arr[i+1] *= -1
				arr[i] *= -1
			}
		}
		return arr[0] == expect
	}
	return check(1) || check(-1)
}
