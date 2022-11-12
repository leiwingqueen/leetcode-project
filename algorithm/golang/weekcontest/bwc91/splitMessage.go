package bwc91

import (
	"fmt"
	"math"
)

func splitMessage(message string, limit int) []string {
	var getLen func(num int) int
	getLen = func(num int) int {
		if num == 0 {
			return 1
		}
		cnt := 0
		for num > 0 {
			num /= 10
			cnt++
		}
		return cnt
	}
	var check func(k int) bool
	check = func(k int) bool {
		idx := 0
		for i := 0; i < k; i++ {
			ext := getLen(k) + getLen(i+1) + 3
			if ext >= limit {
				return false
			}
			left := limit - ext
			idx += left
			if idx >= len(message) {
				return true
			}
		}
		return idx >= len(message)
	}
	l := 1
	r := len(message)
	// 关键是这一步处理
	if getLen(r)*2+3 >= limit {
		p := (limit - 4) / 2
		r = int(math.Pow(10, float64(p))) - 1
	}
	if !check(r) {
		return []string{}
	}
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	k := l
	idx := 0
	res := []string{}
	for i := 0; i < k; i++ {
		from := idx
		ext := getLen(k) + getLen(i+1) + 3
		left := limit - ext
		idx += left
		if idx >= len(message) {
			idx = len(message)
		}
		res = append(res, fmt.Sprintf("%s<%d/%d>", message[from:idx], i+1, k))
	}
	return res
}
