package wc386

import "sort"

func earliestSecondToMarkIndices2(nums []int, changeIndices []int) int {
	n, m := len(nums), len(changeIndices)
	check := func(k int) bool {
		// 查找每个数字最后出现的位置
		lastIdx := make([][]int, n)
		for i, num := range changeIndices[:k] {
			lastIdx[num-1] = []int{num, i + 1}
		}
		// 假设有数字没有出现，直接返回false
		for _, idx := range lastIdx {
			if len(idx) == 0 {
				return false
			}
		}
		// 根据最后一次出现的位置进行排序
		sort.Slice(lastIdx, func(i, j int) bool {
			return lastIdx[i][1] < lastIdx[j][1]
		})
		// 需要保证lastIdx之前有足够的数字删除
		used := 0
		for i, idx := range lastIdx {
			if idx[1]-1-used-i < 2 {
				return false
			}
			used += 2
		}
		return true
	}
	if !check(m) {
		return -1
	}
	l, r := 1, m
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
