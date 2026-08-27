package wc516

import "fmt"

func isPalindromic(s string) bool {
	decode := func(b byte) []int {
		arr := make([]int, 8)
		for i := 0; i < 8; i++ {
			if b&(1<<(7-i)) != 0 {
				arr[i] = 1
			}
		}
		return arr
	}
	var arr []int
	for _, b := range s {
		arr = append(arr, decode(byte(b))...)
	}
	l, r := 0, len(arr)-1
	for l < r {
		if arr[l] != arr[r] {
			return false
		}
		l++
		r--
	}
	return true
}

// 用更加常规的写法
func isPalindromic2(s string) bool {
	arr := make([]byte, 0, len(s)*8)
	for _, b := range s {
		arr = append(arr, fmt.Sprintf("%08b", b)...)
	}
	l, r := 0, len(arr)-1
	for l < r {
		if arr[l] != arr[r] {
			return false
		}
		l++
		r--
	}
	return true
}
