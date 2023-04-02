package bwc101

import "sort"

func makeSubKSumEqual(arr []int, k int) int64 {
	n := len(arr)
	var res int64
	visit := make([]bool, n)
	for i := 0; i < n; i++ {
		if visit[i] {
			continue
		}
		var sum int64
		cnt := 0
		tmp := make([]int, 0)
		for j := i; !visit[j]; j = (j + k) % n {
			visit[j] = true
			sum += int64(arr[j])
			cnt++
			tmp = append(tmp, arr[j])
		}
		// 直接取中位数
		sort.Ints(tmp)
		avg := tmp[cnt/2]
		var r1 int64
		for _, t := range tmp {
			r1 += abs(int64(t) - int64(avg))
		}
		res += r1

	}
	return res
}

func abs(num int64) int64 {
	if num > 0 {
		return num
	} else {
		return -num
	}
}

func makeSubKSumEqual2(arr []int, k int) int64 {
	n := len(arr)
	var res int64
	visit := make([]bool, n)
	for i := 0; i < n; i++ {
		if visit[i] {
			continue
		}
		cnt := 0
		tmp := make([]int, 0)
		for j := i; !visit[j]; j = (j + k) % n {
			visit[j] = true
			cnt++
			tmp = append(tmp, arr[j])
		}
		// 直接取中位数
		sort.Ints(tmp)
		avg := tmp[cnt/2]
		var r1 int64
		for _, t := range tmp {
			r1 += abs(int64(t) - int64(avg))
		}
		res += r1

	}
	return res
}
