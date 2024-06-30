package wc404

func maximumLength(nums []int) int {
	n := len(nums)
	arr := make([]int, n)
	zeroCnt := 0
	firstZeroIdx := -1
	firstOneIdx := -1
	for i, num := range nums {
		arr[i] = num % 2
		if arr[i] == 0 {
			if firstZeroIdx < 0 {
				firstZeroIdx = i
			}
			zeroCnt++
		} else {
			if firstOneIdx < 0 {
				firstOneIdx = i
			}
		}
	}
	oneCnt := n - zeroCnt
	// 假设子序列的模为0
	res := max(zeroCnt, oneCnt)
	// 模为1
	if firstZeroIdx >= 0 {
		tmp := 1
		last := 0
		for _, num := range arr[firstZeroIdx+1:] {
			if last^num == 1 {
				tmp++
				last = num
			}
		}
		res = max(res, tmp)
	}
	if firstOneIdx >= 0 {
		tmp := 1
		last := 1
		for _, num := range arr[firstOneIdx+1:] {
			if last^num == 1 {
				tmp++
				last = num
			}
		}
		res = max(res, tmp)
	}
	return res
}
