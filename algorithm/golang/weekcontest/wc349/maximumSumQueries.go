package wc349

import "sort"

// 朴素解法，超时
func maximumSumQueries(nums1 []int, nums2 []int, queries [][]int) []int {
	n := len(nums1)
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = []int{nums1[i], nums2[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][0] < arr[j][0]
	})
	search := func(x, y int) int {
		idx := sort.Search(n, func(i int) bool {
			return arr[i][0] >= x
		})
		if idx == n {
			return -1
		}
		res := -1
		for i := idx; i < n; i++ {
			if arr[i][1] >= y && (res < 0 || arr[i][0]+arr[i][1] > res) {
				res = arr[i][0] + arr[i][1]
			}
		}
		return res
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		res[i] = search(query[0], query[1])
	}
	return res
}

// 相当于尝试建两个索引看下哪个更优，但是还是超时
func maximumSumQueries2(nums1 []int, nums2 []int, queries [][]int) []int {
	n := len(nums1)
	arr1 := make([][]int, n)
	arr2 := make([][]int, n)
	for i := 0; i < n; i++ {
		arr1[i] = []int{nums1[i], nums2[i]}
		arr2[i] = []int{nums1[i], nums2[i]}
	}
	sort.Slice(arr1, func(i, j int) bool {
		return arr1[i][0] < arr1[j][0]
	})
	sort.Slice(arr2, func(i, j int) bool {
		return arr2[i][1] < arr2[j][1]
	})
	search := func(x, y int) int {
		idx1 := sort.Search(n, func(i int) bool {
			return arr1[i][0] >= x
		})
		idx2 := sort.Search(n, func(i int) bool {
			return arr2[i][1] >= y
		})
		if idx1 == n || idx2 == n {
			return -1
		}
		res := -1
		if idx1 > idx2 {
			for i := idx1; i < n; i++ {
				if arr1[i][1] >= y && (res < 0 || arr1[i][0]+arr1[i][1] > res) {
					res = arr1[i][0] + arr1[i][1]
				}
			}
		} else {
			for i := idx2; i < n; i++ {
				if arr2[i][0] >= x && (res < 0 || arr2[i][0]+arr2[i][1] > res) {
					res = arr2[i][0] + arr2[i][1]
				}
			}
		}
		return res
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		res[i] = search(query[0], query[1])
	}
	return res
}

// 过滤掉各种数据集之后就能过了
func maximumSumQueries3(nums1 []int, nums2 []int, queries [][]int) []int {
	n := len(nums1)
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = []int{nums1[i], nums2[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		if arr[i][0] != arr[j][0] {
			return arr[i][0] < arr[j][0]
		} else {
			return arr[i][1] > arr[j][1]
		}
	})
	// 过滤掉不必要的数据
	var arr2 [][]int
	l := 0
	for l < n {
		item := arr[l]
		if len(arr2) == 0 {
			arr2 = append(arr2, item)
			l++
		} else {
			pre := arr2[len(arr2)-1]
			if pre[0] != item[0] {
				if pre[1] > item[1] {
					arr2 = append(arr2, item)
					l++
				} else {
					arr2 = arr2[:len(arr2)-1]
				}
			} else {
				// 相同跳过
				l++
			}
		}
	}
	n = len(arr2)
	search := func(x, y int) int {
		idx := sort.Search(n, func(i int) bool {
			return arr2[i][0] >= x
		})
		if idx == n {
			return -1
		}
		res := -1
		for i := idx; i < n; i++ {
			if arr2[i][1] >= y && (res < 0 || arr2[i][0]+arr2[i][1] > res) {
				res = arr2[i][0] + arr2[i][1]
			}
		}
		return res
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		res[i] = search(query[0], query[1])
	}
	return res
}
