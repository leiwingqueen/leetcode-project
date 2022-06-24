package hash

//桶排序，统计数字出现的数量，不为1即为目标
func findErrorNums(nums []int) []int {
	bucket := make([]int, len(nums))
	res := []int{0, 0}
	for _, n := range nums {
		bucket[n-1]++
		if bucket[n-1] > 1 {
			res[0] = n
		}
	}
	//统计数量为0的
	for i := 0; i < len(nums); i++ {
		if bucket[i] == 0 {
			res[1] = i + 1
		}
	}
	return res
}
