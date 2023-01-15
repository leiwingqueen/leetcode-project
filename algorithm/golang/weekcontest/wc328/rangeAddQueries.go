package wc328

func rangeAddQueries(n int, queries [][]int) [][]int {
	arr := make([][]int, n)
	for i := 0; i < n; i++ {
		arr[i] = make([]int, n)
	}
	for _, query := range queries {
		for i := query[0]; i <= query[2]; i++ {
			for j := query[1]; j <= query[3]; j++ {
				arr[i][j]++
			}
		}
	}
	return arr
}

// 差分数组，二维不会写，只能先用一维的
func rangeAddQueries2(n int, queries [][]int) [][]int {
	arr := make([]int, n*n+1)
	for _, query := range queries {
		r1 := query[0]
		c1 := query[1]
		r2 := query[2]
		c2 := query[3]
		for i := r1; i <= r2; i++ {
			arr[i*n+c1] += 1
			arr[i*n+c2+1] -= 1
		}
		// println(fmt.Sprintf("arr:%v", arr))
	}
	res := make([]int, n*n+1)
	for i := 0; i < n*n; i++ {
		res[i+1] = res[i] + arr[i]
	}
	res1 := make([][]int, n)
	for i := 0; i < n; i++ {
		res1[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			res1[i][j] = res[i*n+j+1]
		}
	}
	return res1
}
