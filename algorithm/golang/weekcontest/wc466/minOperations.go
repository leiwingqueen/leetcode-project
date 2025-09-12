package wc466

func minOperations(nums []int) int {
	equal := true
	for i := 1; i < len(nums); i++ {
		if nums[i] != nums[0] {
			equal = false
			break
		}
	}
	if equal {
		return 0
	} else {
		return 1
	}
}
