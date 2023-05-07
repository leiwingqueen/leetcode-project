package wc344

func colorTheArray(n int, queries [][]int) []int {
	arr := make([]int, n)
	res := make([]int, len(queries))
	cnt := 0
	for i, query := range queries {
		idx, color := query[0], query[1]
		if arr[idx] != color {
			if idx > 0 && arr[idx-1] > 0 {
				if arr[idx-1] == arr[idx] {
					cnt--
				} else {
					if arr[idx-1] == color {
						cnt++
					}
				}
			}
			if idx < n-1 && arr[idx+1] > 0 {
				if arr[idx+1] == arr[idx] {
					cnt--
				} else {
					if arr[idx+1] == color {
						cnt++
					}
				}
			}
		}
		arr[idx] = color
		res[i] = cnt
	}
	return res
}
