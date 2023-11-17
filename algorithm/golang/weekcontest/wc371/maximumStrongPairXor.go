package wc371

import "sort"

// 给你一个下标从 0 开始的整数数组 nums 。如果一对整数 x 和 y 满足以下条件，则称其为 强数对 ：
//
//|x - y| <= min(x, y)
//你需要从 nums 中选出两个整数，且满足：这两个整数可以形成一个强数对，并且它们的按位异或（XOR）值是在该数组所有强数对中的 最大值 。
//
//返回数组 nums 所有可能的强数对中的 最大 异或值。
//
//注意，你可以选择同一个整数两次来形成一个强数对。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3,4,5]
//输出：7
//解释：数组 nums 中有 11 个强数对：(1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 3), (3, 4), (3, 5), (4, 4), (4, 5) 和 (5, 5) 。
//这些强数对中的最大异或值是 3 XOR 4 = 7 。
//示例 2：
//
//输入：nums = [10,100]
//输出：0
//解释：数组 nums 中有 2 个强数对：(10, 10) 和 (100, 100) 。
//这些强数对中的最大异或值是 10 XOR 10 = 0 ，数对 (100, 100) 的异或值也是 100 XOR 100 = 0 。
//示例 3：
//
//输入：nums = [500,520,2500,3000]
//输出：1020
//解释：数组 nums 中有 6 个强数对：(500, 500), (500, 520), (520, 520), (2500, 2500), (2500, 3000) 和 (3000, 3000) 。
//这些强数对中的最大异或值是 500 XOR 520 = 1020 ；另一个异或值非零的数对是 (5, 6) ，其异或值是 2500 XOR 3000 = 636 。
//
//
//提示：
//
//1 <= nums.length <= 5 * 104
//1 <= nums[i] <= 220 - 1

func maximumStrongPairXor(nums []int) int {
	abs := func(a int) int {
		if a < 0 {
			return -a
		} else {
			return a
		}
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			sub := abs(nums[i] - nums[j])
			if sub <= min(nums[i], nums[j]) && (nums[i]^nums[j]) > res {
				res = nums[i] ^ nums[j]
			}
		}
	}
	return res
}

// 二进制枚举，这个确实不容易想到，太巧妙了
func maximumStrongPairXor2(nums []int) int {
	sort.Ints(nums)
	mask := 0
	res := 0
	for i := 20; i >= 0; i-- {
		mask |= 1 << i
		mp := make(map[int]int)
		for _, num := range nums {
			y := num & mask
			if x, ok := mp[y^(res|1<<i)]; ok && 2*x >= num {
				res |= 1 << i
				break
			}
			mp[y] = num
		}
	}
	return res
}
