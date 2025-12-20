package bwc170

func minimumFlips(n int) int {
	if n == 0 {
		return 0
	}
	var arr []int
	for n > 0 {
		arr = append(arr, n&0b01)
		n >>= 1
	}
	l, r := 0, len(arr)-1
	res := 0
	for l < r {
		if arr[l] != arr[r] {
			res += 2
		}
		l++
		r--
	}
	return res
}
