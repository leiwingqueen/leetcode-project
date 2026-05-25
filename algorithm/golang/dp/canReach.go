package dp

import "sort"

// 给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。一开始，你在下标 0 处，且该位置的值一定为 '0' 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
//
//i + minJump <= j <= min(i + maxJump, s.length - 1) 且
//s[j] == '0'.
//如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
//
//
//
//示例 1：
//
//输入：s = "011010", minJump = 2, maxJump = 3
//输出：true
//解释：
//第一步，从下标 0 移动到下标 3 。
//第二步，从下标 3 移动到下标 5 。
//示例 2：
//
//输入：s = "01101110", minJump = 2, maxJump = 3
//输出：false
//
//
//提示：
//
//2 <= s.length <= 105
//s[i] 要么是 '0' ，要么是 '1'
//s[0] == '0'
//1 <= minJump <= maxJump < s.length

func canReach3(s string, minJump int, maxJump int) bool {
	if s[len(s)-1] == '1' {
		return false
	}
	var arr []int
	for i := 0; i < len(s); i++ {
		if s[i] == '0' {
			arr = append(arr, i)
		}
	}
	n := len(arr)
	reach := make([]bool, n)
	reach[0] = true
	for i := 1; i < n; i++ {
		// 计算能够到arr[i]的左右边界。[arr[i]-maxJump,arr[i]-minJump]
		l := sort.Search(n, func(j int) bool {
			return arr[j] >= arr[i]-maxJump
		})
		if l >= i {
			continue
		}
		r := sort.Search(n, func(j int) bool {
			return arr[j] > arr[i]-minJump
		})
		if r > i {
			r = i
		}
		// [l,r)区间只要有一个是true即可，这里其实也可以用前缀和优化
		for j := l; j < r; j++ {
			if reach[j] {
				reach[i] = true
				break
			}
		}
	}
	return reach[n-1]
}

func canReach4(s string, minJump int, maxJump int) bool {
	if s[len(s)-1] == '1' {
		return false
	}
	var arr []int
	for i := 0; i < len(s); i++ {
		if s[i] == '0' {
			arr = append(arr, i)
		}
	}
	n := len(arr)
	prefix := make([]int, n+1)
	prefix[1] = 1
	for i := 1; i < n; i++ {
		prefix[i+1] = prefix[i]
		// 计算能够到arr[i]的左右边界。[arr[i]-maxJump,arr[i]-minJump]
		l := sort.Search(n, func(j int) bool {
			return arr[j] >= arr[i]-maxJump
		})
		if l >= i {
			continue
		}
		r := sort.Search(n, func(j int) bool {
			return arr[j] > arr[i]-minJump
		})
		if r > i {
			r = i
		}
		// [l,r)区间只要有一个是true即可，这里其实也可以用前缀和优化
		cnt := prefix[r] - prefix[l]
		if cnt > 0 {
			prefix[i+1]++
		}
	}
	return prefix[n]-prefix[n-1] > 0
}
