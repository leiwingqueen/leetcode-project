package wc372

func leftmostBuildingQueries(heights []int, queries [][]int) []int {
	n := len(heights)
	find := func(query []int) int {
		i, j := query[0], query[1]
		for l := j; l < n; l++ {
			if heights[l] >= heights[i] && heights[l] >= heights[j] {
				return l
			}
		}
		return -1
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		res[i] = find(query)
	}
	return res
}
