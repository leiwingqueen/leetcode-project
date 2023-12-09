package bwc119

func findIntersectionValues(nums1 []int, nums2 []int) []int {
	mp1, mp2 := make(map[int]bool), make(map[int]bool)
	for _, num := range nums1 {
		mp1[num] = true
	}
	for _, num := range nums2 {
		mp2[num] = true
	}
	cnt1, cnt2 := 0, 0
	for _, num := range nums1 {
		if mp2[num] {
			cnt1++
		}
	}
	for _, num := range nums2 {
		if mp1[num] {
			cnt2++
		}
	}
	return []int{cnt1, cnt2}
}
