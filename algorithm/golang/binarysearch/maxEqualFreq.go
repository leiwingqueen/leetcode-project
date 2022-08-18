package binarysearch

import "sort"

//给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
//
//从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
//如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
//
//
//
//示例 1：
//
//输入：nums = [2,2,1,1,5,3,3,5]
//输出：7
//解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
//示例 2：
//
//输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
//输出：13
//
//
//提示：
//
//2 <= nums.length <= 105
//1 <= nums[i] <= 105
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-equal-frequency
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 思路错误
func maxEqualFreq(nums []int) int {
	l := 1
	r := len(nums)
	var check func(size int) bool
	check = func(size int) bool {
		mp := make(map[int]int)
		for i := 0; i < size; i++ {
			mp[nums[i]]++
		}
		mp2 := make(map[int]int)
		for _, v := range mp {
			mp2[v]++
		}
		if len(mp2) > 2 {
			return false
		}
		if len(mp2) == 1 {
			first := 0
			for k, _ := range mp2 {
				first = k
				break
			}
			return mp2[first] == 1
		}
		s := make([][]int, 0)
		for k, v := range mp2 {
			s = append(s, []int{k, v})
		}
		sort.Slice(s, func(i, j int) bool {
			return s[i][1] < s[j][1]
		})
		return s[0][1] == 1
	}
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}

func maxEqualFreq2(nums []int) int {
	mp1 := make(map[int]int)
	for _, num := range nums {
		mp1[num]++
	}
	mp2 := make(map[int]int)
	for _, v := range mp1 {
		mp2[v]++
	}
	n := len(nums)
	var check func(size int) bool
	check = func(size int) bool {
		if len(mp2) > 2 {
			return false
		}
		var arr [][]int
		for k, v := range mp2 {
			arr = append(arr, []int{k, v})
		}
		if len(arr) == 1 {
			return arr[0][0] == 1 || arr[0][1] == 1
		}
		if arr[0][1] != 1 && arr[1][1] != 1 {
			return false
		}
		if arr[0][0] == 1 || arr[1][0] == 1 {
			return true
		}
		//只能从数量唯一的进行处理
		return arr[0][1] == 1 && arr[0][0]-1 == arr[1][0] || arr[1][1] == 1 && arr[1][0]-1 == arr[0][0]
	}
	for n > 0 {
		if check(n) {
			return n
		}
		n--
		num := nums[n]
		pre := mp1[num]
		mp1[num]--
		if mp1[num] > 0 {
			mp2[mp1[num]]++
		}
		mp2[pre]--
		if mp2[pre] == 0 {
			delete(mp2, pre)
		}
	}
	return 0
}
