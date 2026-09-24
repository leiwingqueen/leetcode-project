package wc520

import "sort"

// 这个数据量只能用二分查找
func countIntersectingIntervals2(intervals [][]int) int64 {
	n := len(intervals)
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		arr[i] = i
	}
	sort.Slice(arr, func(i, j int) bool {
		i1, i2 := intervals[arr[i]], intervals[arr[j]]
		return i1[1] < i2[1]
	})
	// 找到第一个>=limit的下标
	search := func(right int, limit int) int {
		l, r := 0, right
		for l <= r {
			mid := l + (r-l)/2
			interval := intervals[arr[mid]]
			if interval[1] >= limit {
				r = mid
				if l == r {
					break
				}
			} else {
				l = mid + 1
			}
		}
		return right - l + 1
	}
	var res int64
	for i := 1; i < n; i++ {
		interval := intervals[arr[i]]
		cnt := search(i-1, interval[0])
		res += int64(cnt)
	}
	return res
}
