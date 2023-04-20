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
