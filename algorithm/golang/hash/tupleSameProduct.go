package hash

import "sort"

// 给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
//
//
//
//示例 1：
//
//输入：nums = [2,3,4,6]
//输出：8
//解释：存在 8 个满足题意的元组：
//(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
//(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
//示例 2：
//
//输入：nums = [1,2,4,5,10]
//输出：16
//解释：存在 16 个满足题意的元组：
//(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
//(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
//(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
//(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
//
//
//提示：
//
//1 <= nums.length <= 1000
//1 <= nums[i] <= 104
//nums 中的所有元素 互不相同

// 超时
func tupleSameProduct(nums []int) int {
	n := len(nums)
	res := 0
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
	}
	sort.Ints(nums)
	for p1 := 0; p1 <= n-3; p1++ {
		for p2 := n - 1; p2 >= p1+3; p2-- {
			s := nums[p1] * nums[p2]
			for p3 := p1 + 1; p3 < p2-1; p3++ {
				if s%nums[p3] == 0 && mp[s/nums[p3]] && nums[p3] < s/nums[p3] {
					res++
				}
			}
		}
	}
	return res * 8
}

func tupleSameProduct2(nums []int) int {
	n := len(nums)
	mp := make(map[int]int)
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			mp[nums[i]*nums[j]]++
		}
	}
	res := 0
	for _, cnt := range mp {
		res += 4 * cnt * (cnt - 1)
	}
	return res
}
