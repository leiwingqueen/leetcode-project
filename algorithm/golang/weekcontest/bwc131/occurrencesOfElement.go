package bwc131

func occurrencesOfElement(nums []int, queries []int, x int) []int {
	var arr []int
	for i, num := range nums {
		if num == x {
			arr = append(arr, i)
		}
	}
	res := make([]int, len(queries))
	for i, query := range queries {
		if query-1 < len(arr) {
			res[i] = arr[query-1]
		} else {
			res[i] = -1
		}
	}
	return res
}
