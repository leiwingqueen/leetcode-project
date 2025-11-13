package wc475

// 给你一个整数数组 nums。
//
// 如果满足 nums[i] == nums[j] == nums[k]，且 (i, j, k) 是 3 个 不同 下标，那么三元组 (i, j, k) 被称为 有效三元组 。
//
// 有效三元组 的 距离 被定义为 abs(i - j) + abs(j - k) + abs(k - i)，其中 abs(x) 表示 x 的 绝对值 。
//
// 返回一个整数，表示 有效三元组 的 最小 可能距离。如果不存在 有效三元组 ，返回 -1。
//
// 示例 1：
//
// 输入： nums = [1,2,1,1,3]
//
// 输出： 6
//
// 解释：
//
// 最小距离对应的有效三元组是 (0, 2, 3) 。
//
// (0, 2, 3) 是一个有效三元组，因为 nums[0] == nums[2] == nums[3] == 1。它的距离为 abs(0 - 2) + abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6。
//
// 示例 2：
//
// 输入： nums = [1,1,2,3,2,1,2]
//
// 输出： 8
//
// 解释：
//
// 最小距离对应的有效三元组是 (2, 4, 6) 。
//
// (2, 4, 6) 是一个有效三元组，因为 nums[2] == nums[4] == nums[6] == 2。它的距离为 abs(2 - 4) + abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8。
//
// 示例 3：
//
// 输入： nums = [1]
//
// 输出： -1
//
// 解释：
//
// 不存在有效三元组，因此答案为 -1。
//
// 提示：
//
// 1 <= n == nums.length <= 100
// 1 <= nums[i] <= n

func minimumDistance(nums []int) int {
	mp := make(map[int][]int)
	for i, num := range nums {
		mp[num] = append(mp[num], i)
	}
	res := -1
	for _, arr := range mp {
		if len(arr) >= 3 {
			for i := 0; i < len(arr)-2; i++ {
				r := (arr[i+2] - arr[i]) * 2
				if res < 0 || r < res {
					res = r
				}
			}
		}
	}
	return res
}
