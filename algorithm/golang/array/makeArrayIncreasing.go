package array

import "sort"

// 给你两个整数数组 arr1 和 arr2，返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
//
//每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length，然后进行赋值运算 arr1[i] = arr2[j]。
//
//如果无法让 arr1 严格递增，请返回 -1。
//
//
//
//示例 1：
//
//输入：arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
//输出：1
//解释：用 2 来替换 5，之后 arr1 = [1, 2, 3, 6, 7]。
//示例 2：
//
//输入：arr1 = [1,5,3,6,7], arr2 = [4,3,1]
//输出：2
//解释：用 3 来替换 5，然后用 4 来替换 3，得到 arr1 = [1, 3, 4, 6, 7]。
//示例 3：
//
//输入：arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
//输出：-1
//解释：无法使 arr1 严格递增。
//
//
//提示：
//
//1 <= arr1.length, arr2.length <= 2000
//0 <= arr1[i], arr2[i] <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/make-array-strictly-increasing
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 先贪心，但不是最优解
func makeArrayIncreasing(arr1 []int, arr2 []int) int {
	sort.Ints(arr2)
	cnt := 0
	if arr2[0] < arr1[0] {
		arr1[0] = arr2[0]
		cnt++
	}
	for i := 1; i < len(arr1); i++ {
		idx := sort.Search(len(arr2), func(j int) bool {
			return arr2[j] > arr1[i-1]
		})
		if idx < len(arr2) && arr2[idx] < arr1[i] {
			arr1[i] = arr2[idx]
			cnt++
		}
		if arr1[i] <= arr1[i-1] {
			return -1
		}
	}
	return cnt
}

// 勉强通过
func makeArrayIncreasing2(arr1 []int, arr2 []int) int {
	sort.Ints(arr2)
	n := len(arr1)
	last := arr1[n-1]
	if arr2[len(arr2)-1] > last {
		last = arr2[len(arr2)-1]
	}
	cache := make(map[int]map[int]int)
	var dfs func(k, mx int) int
	dfs = func(k, mx int) int {
		if k == 0 {
			return 0
		}
		_, e1 := cache[k]
		if e1 {
			v2, e2 := cache[k][mx]
			if e2 {
				return v2
			}
		} else {
			cache[k] = make(map[int]int)
		}
		res := -1
		// 不修改
		if arr1[k-1] <= mx {
			sub := dfs(k-1, arr1[k-1]-1)
			if sub >= 0 {
				res = sub
			}
		}
		// 找到第一个>mx的下标
		idx := sort.Search(len(arr2), func(i int) bool {
			return arr2[i] > mx
		})
		// 所有值都>mx，证明没有合适的更换
		if idx == 0 {
			return res
		}
		sub := dfs(k-1, arr2[idx-1]-1)
		if sub >= 0 && (res < 0 || sub+1 < res) {
			res = sub + 1
		}
		cache[k][mx] = res
		return res
	}
	return dfs(n, last)
}
