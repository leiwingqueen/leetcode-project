package wc395

// 给你一个整数数组 nums 。数组 nums 的 唯一性数组 是一个按元素从小到大排序的数组，包含了 nums 的所有
//非空子数组中
//不同元素的个数。
//
//换句话说，这是由所有 0 <= i <= j < nums.length 的 distinct(nums[i..j]) 组成的递增数组。
//
//其中，distinct(nums[i..j]) 表示从下标 i 到下标 j 的子数组中不同元素的数量。
//
//返回 nums 唯一性数组 的 中位数 。
//
//注意，数组的 中位数 定义为有序数组的中间元素。如果有两个中间元素，则取值较小的那个。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3]
//
//输出：1
//
//解释：
//
//nums 的唯一性数组为 [distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])]，即 [1, 1, 1, 2, 2, 3] 。唯一性数组的中位数为 1 ，因此答案是 1 。
//
//示例 2：
//
//输入：nums = [3,4,3,4,5]
//
//输出：2
//
//解释：
//
//nums 的唯一性数组为 [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3] 。唯一性数组的中位数为 2 ，因此答案是 2 。
//
//示例 3：
//
//输入：nums = [4,3,5,4]
//
//输出：2
//
//解释：
//
//nums 的唯一性数组为 [1, 1, 1, 1, 2, 2, 2, 3, 3, 3] 。唯一性数组的中位数为 2 ，因此答案是 2 。
//
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 105

func medianOfUniquenessArray(nums []int) int {
	n := len(nums)
	// distinct<=x的子数组的数量
	count := func(x int) int64 {
		l, r := 0, 0
		mp := make(map[int]int)
		var cnt int64
		for l < n {
			for r < n && len(mp) <= x {
				mp[nums[r]]++
				r++
			}
			// 统计[l,r)
			cnt += int64(r - l)
			mp[nums[l]]--
			if mp[nums[l]] == 0 {
				delete(mp, nums[l])
			}
			l++
		}
		return cnt
	}
	// 二分查找
	mp := make(map[int]struct{})
	for _, num := range nums {
		mp[num] = struct{}{}
	}
	maxR := len(mp)
	k := int64(n) * int64(n+1)
	// > target的第一个结果
	target := (k - 1) / 2
	l, r := 1, maxR
	for l < r {
		mid := l + (r-l)/2
		if count(mid) > target {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
