package binarysearch

// 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
//
//一个子数组指的是原数组中连续的一个子序列。
//
//请你返回满足题目要求的最短子数组的长度。
//
//
//
//示例 1：
//
//输入：arr = [1,2,3,10,4,2,3,5]
//输出：3
//解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
//另一个正确的解为删除子数组 [3,10,4] 。
//示例 2：
//
//输入：arr = [5,4,3,2,1]
//输出：4
//解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
//示例 3：
//
//输入：arr = [1,2,3]
//输出：0
//解释：数组已经是非递减的了，我们不需要删除任何元素。
//示例 4：
//
//输入：arr = [1]
//输出：0
//
//
//提示：
//
//1 <= arr.length <= 10^5
//0 <= arr[i] <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 超时
func findLengthOfShortestSubarray(arr []int) int {
	n := len(arr)
	check2 := func(k int, i int) bool {
		// 检查[0,i)，[i+k,n)
		for j := 1; j < i; j++ {
			if arr[j] < arr[j-1] {
				return false
			}
		}
		for j := i + k + 1; j < n; j++ {
			// 检查[0,i)，[i+k-1,n)
			if arr[j] < arr[j-1] {
				return false
			}
		}
		if i-1 < 0 || i+k >= n {
			return true
		} else {
			return arr[i+k] >= arr[i-1]
		}
	}
	check := func(k int) bool {
		for i := 0; i <= n-k; i++ {
			if check2(k, i) {
				return true
			}
		}
		return false
	}
	l, r := 0, len(arr)-1
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

// 双指针
func findLengthOfShortestSubarray2(arr []int) int {
	n := len(arr)
	if n <= 1 {
		return 0
	}
	// 找到最长的前缀和后缀
	p1 := 1
	for ; p1 < n; p1++ {
		if arr[p1] < arr[p1-1] {
			break
		}
	}
	if p1 == n {
		return 0
	}
	p2 := n - 2
	for ; p2 >= 0; p2-- {
		if arr[p2] > arr[p2+1] {
			break
		}
	}
	// 前缀[0,p1),后缀[p2+1,n)
	// corner case. 只保留前缀或者后缀
	res := n - p1
	if p2+1 < res {
		res = p2 + 1
	}
	// 遍历所有的前缀尝试从l位置删除
	l, r := 0, p2+1
	for ; l < p1; l++ {
		// 找到第一个arr[r]>=arr[l]
		for ; r < n; r++ {
			if arr[r] >= arr[l] {
				break
			}
		}
		// 删除的范围[l+1,r)
		if r-l-1 < res {
			res = r - l - 1
		}
	}
	return res
}
