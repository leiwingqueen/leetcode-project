package array

import (
	"math"
	"sort"
)

//给你个整数数组 arr，其中每个元素都 不相同。
//
//请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
//
//
//
//示例 1：
//
//输入：arr = [4,2,1,3]
//输出：[[1,2],[2,3],[3,4]]
//示例 2：
//
//输入：arr = [1,3,6,10,15]
//输出：[[1,3]]
//示例 3：
//
//输入：arr = [3,8,-10,23,19,-4,-14,27]
//输出：[[-14,-10],[19,23],[23,27]]
//
//
//提示：
//
//2 <= arr.length <= 10^5
//-10^6 <= arr[i] <= 10^6
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-absolute-difference
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//两次扫描
func minimumAbsDifference(arr []int) [][]int {
	sort.Ints(arr)
	min := math.MaxInt32
	for i := 0; i < len(arr)-1; i++ {
		sub := arr[i+1] - arr[i]
		if sub < min {
			min = sub
		}
	}
	res := make([][]int, 0)
	for i := 0; i < len(arr)-1; i++ {
		sub := arr[i+1] - arr[i]
		if sub == min {
			res = append(res, []int{arr[i], arr[i+1]})
		}
	}
	return res
}

//一次遍历
func minimumAbsDifference2(arr []int) [][]int {
	sort.Ints(arr)
	min := math.MaxInt32
	res := make([][]int, 0)
	for i := 0; i < len(arr)-1; i++ {
		sub := arr[i+1] - arr[i]
		if sub > min {
			continue
		}
		if sub < min {
			min = sub
			res = [][]int{{arr[i], arr[i+1]}}
		} else {
			res = append(res, []int{arr[i], arr[i+1]})
		}
	}
	return res
}
