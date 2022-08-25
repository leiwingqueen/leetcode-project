package array

import "sort"

// 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
//
//整数 a 比整数 b 更接近 x 需要满足：
//
//|a - x| < |b - x| 或者
//|a - x| == |b - x| 且 a < b
//
//
//示例 1：
//
//输入：arr = [1,2,3,4,5], k = 4, x = 3
//输出：[1,2,3,4]
//示例 2：
//
//输入：arr = [1,2,3,4,5], k = 4, x = -1
//输出：[1,2,3,4]
//
//
//提示：
//
//1 <= k <= arr.length
//1 <= arr.length <= 104
//arr 按 升序 排列
//-104 <= arr[i], x <= 104
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-k-closest-elements
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 排序解法
func findClosestElements(arr []int, k int, x int) []int {
	sort.Slice(arr, func(i, j int) bool {
		if abs(arr[i]-x) < abs(arr[j]-x) {
			return true
		} else if abs(arr[i]-x) == abs(arr[j]-x) {
			return arr[i] < arr[j]
		} else {
			return false
		}
	})
	sort.Ints(arr[:k])
	return arr[:k]
}

// 二分+双指针
func findClosestElements2(arr []int, k int, x int) []int {
	// 找到第一个>=x的下标
	n := len(arr)
	l := 0
	r := n - 1
	for l < r {
		mid := l + (r-l)/2
		if arr[mid] >= x {
			r = mid
		} else {
			l = mid + 1
		}
	}
	if arr[l] < x {
		return arr[n-k : n]
	}
	p1 := l
	p2 := l
	for p2-p1 < k {
		if p1 == 0 {
			p2++
		} else if p2 == n {
			p1--
		} else {
			if abs(arr[p1-1]-x) <= abs(arr[p2]-x) {
				p1--
			} else {
				p2++
			}
		}
	}
	return arr[p1:p2]
}
