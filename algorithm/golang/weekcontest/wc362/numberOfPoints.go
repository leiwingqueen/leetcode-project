package wc362

import "sort"

// 给你一个下标从 0 开始的二维整数数组 nums 表示汽车停放在数轴上的坐标。对于任意下标 i，nums[i] = [starti, endi] ，其中 starti 是第 i 辆车的起点，endi 是第 i 辆车的终点。
//
//返回数轴上被车 任意部分 覆盖的整数点的数目。
//
//
//
//示例 1：
//
//输入：nums = [[3,6],[1,5],[4,7]]
//输出：7
//解释：从 1 到 7 的所有点都至少与一辆车相交，因此答案为 7 。
//示例 2：
//
//输入：nums = [[1,3],[5,8]]
//输出：7
//解释：1、2、3、5、6、7、8 共计 7 个点满足至少与一辆车相交，因此答案为 7 。
//
//
//提示：
//
//1 <= nums.length <= 100
//nums[i].length == 2
//1 <= starti <= endi <= 100

func numberOfPoints(nums [][]int) int {
	cnt := make([]bool, 101)
	for _, r := range nums {
		start, end := r[0], r[1]
		for i := start; i <= end; i++ {
			cnt[i] = true
		}
	}
	res := 0
	for _, c := range cnt {
		if c {
			res++
		}
	}
	return res
}

func numberOfPoints2(nums [][]int) int {
	sort.Slice(nums, func(i, j int) bool {
		if nums[i][0] != nums[j][0] {
			return nums[i][0] < nums[j][0]
		}
		return nums[i][1] < nums[j][1]
	})
	res := 0
	last := 0
	for _, r := range nums {
		start, end := r[0], r[1]
		if start > last {
			res += end - start + 1
			last = end
		} else {
			if end > last {
				res += end - last
				last = end
			}
		}
	}
	return res
}
