package dp

import (
	"fmt"
	"sort"
)

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
//
//注意：答案中不可以包含重复的三元组。
//
//
//
//示例 1：
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//示例 2：
//
//输入：nums = []
//输出：[]
//示例 3：
//
//输入：nums = [0]
//输出：[]
//
//
//提示：
//
//0 <= nums.length <= 3000
//-105 <= nums[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/3sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func threeSum(nums []int) [][]int {
	n := len(nums)
	if n < 3 {
		return [][]int{}
	}
	sort.Ints(nums)
	var res [][]int
	mp2 := make(map[string]bool)
	for i := 2; i < n; i++ {
		//转变成两数之和的问题
		mp := make(map[int]bool)
		for j := 0; j < i; j++ {
			want := -nums[i] - nums[j]
			if mp[want] && !mp2[fmt.Sprintf("%d_%d", nums[j], nums[i])] {
				mp2[fmt.Sprintf("%d_%d", nums[j], nums[i])] = true
				res = append(res, []int{-nums[i] - nums[j], nums[j], nums[i]})
			}
			mp[nums[j]] = true
		}
	}
	return res
}

//双指针去重
func threeSum2(nums []int) [][]int {
	n := len(nums)
	if n < 3 {
		return [][]int{}
	}
	sort.Ints(nums)
	var res [][]int
	for i := 0; i < n-2; i++ {
		//去重处理
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		//转变成两数之和的问题
		mp := make(map[int]bool)
		for j := i + 1; j < n; j++ {
			//去重处理
			if j > i+1 && nums[j] == nums[j-1] {
				continue
			}
			want := -nums[i] - nums[j]
			if mp[want] {
				res = append(res, []int{-nums[i] - nums[j], nums[j], nums[i]})
			}
			mp[nums[j]] = true
		}
	}
	return res
}
