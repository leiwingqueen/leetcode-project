package wc373

import "sort"

// 给你一个下标从 0 开始的 正整数 数组 nums 和一个 正整数 limit 。
//
//在一次操作中，你可以选择任意两个下标 i 和 j，如果 满足 |nums[i] - nums[j]| <= limit ，则交换 nums[i] 和 nums[j] 。
//
//返回执行任意次操作后能得到的 字典序最小的数组 。
//
//如果在数组 a 和数组 b 第一个不同的位置上，数组 a 中的对应字符比数组 b 中的对应字符的字典序更小，则认为数组 a 就比数组 b 字典序更小。例如，数组 [2,10,3] 比数组 [10,2,3] 字典序更小，下标 0 处是两个数组第一个不同的位置，且 2 < 10 。
//
//
//
//示例 1：
//
//输入：nums = [1,5,3,9,8], limit = 2
//输出：[1,3,5,8,9]
//解释：执行 2 次操作：
//- 交换 nums[1] 和 nums[2] 。数组变为 [1,3,5,9,8] 。
//- 交换 nums[3] 和 nums[4] 。数组变为 [1,3,5,8,9] 。
//即便执行更多次操作，也无法得到字典序更小的数组。
//注意，执行不同的操作也可能会得到相同的结果。
//示例 2：
//
//输入：nums = [1,7,6,18,2,1], limit = 3
//输出：[1,6,7,18,1,2]
//解释：执行 3 次操作：
//- 交换 nums[1] 和 nums[2] 。数组变为 [1,6,7,18,2,1] 。
//- 交换 nums[0] 和 nums[4] 。数组变为 [2,6,7,18,1,1] 。
//- 交换 nums[0] 和 nums[5] 。数组变为 [1,6,7,18,1,2] 。
//即便执行更多次操作，也无法得到字典序更小的数组。
//示例 3：
//
//输入：nums = [1,7,28,19,10], limit = 3
//输出：[1,7,28,19,10]
//解释：[1,7,28,19,10] 是字典序最小的数组，因为不管怎么选择下标都无法执行操作。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= limit <= 109

// 很挫的写法，勉强过了
func lexicographicallySmallestArray(nums []int, limit int) []int {
	n := len(nums)
	arr := make([][]int, n)
	for i, num := range nums {
		arr[i] = []int{i, num}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][1] < arr[j][1]
	})
	res := make([]int, n)
	l, r := 0, 0
	var pos []int
	for r < n {
		if r > l && arr[r][1]-arr[r-1][1] > limit {
			// [l,r)之间的数字可以重排序
			sort.Ints(pos)
			for i := l; i < r; i++ {
				res[pos[i-l]] = arr[i][1]
			}
			pos = pos[:0]
			l = r
		} else {
			pos = append(pos, arr[r][0])
			r++
		}
	}
	if len(pos) > 0 {
		sort.Ints(pos)
		for i := l; i < r; i++ {
			res[pos[i-l]] = arr[i][1]
		}
	}
	return res
}
