package array

// 给定 m 个数组，每个数组都已经按照升序排好序了。
//
// 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
//
// 返回最大距离。
//
// 示例 1：
//
// 输入：[[1,2,3],[4,5],[1,2,3]]
// 输出：4
// 解释：
// 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
// 示例 2：
//
// 输入：arrays = [[1],[1]]
// 输出：0
//
// 提示：
//
// m == arrays.length
// 2 <= m <= 105
// 1 <= arrays[i].length <= 500
// -104 <= arrays[i][j] <= 104
// arrays[i] 以 升序 排序。
// 所有数组中最多有 105 个整数。

// 贪心，超时是必然的
func maxDistance3(arrays [][]int) int {
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	m := len(arrays)
	res := 0
	for i := 0; i < m; i++ {
		for j := i + 1; j < m; j++ {
			arr1, arr2 := arrays[i], arrays[j]
			dis := max(abs(arr2[len(arr2)-1]-arr1[0]), abs(arr1[len(arr1)-1]-arr2[0]))
			res = max(res, dis)
		}
	}
	return res
}

func maxDistance4(arrays [][]int) int {
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	m := len(arrays)
	res := 0
	minVal, maxVal := arrays[0][0], arrays[0][len(arrays[0])-1]
	for i := 1; i < m; i++ {
		arr := arrays[i]
		res = max(res, max(abs(arr[len(arr)-1]-minVal), abs(maxVal-arr[0])))
		minVal = min(minVal, arr[0])
		maxVal = max(maxVal, arr[len(arr)-1])
	}
	return res
}
