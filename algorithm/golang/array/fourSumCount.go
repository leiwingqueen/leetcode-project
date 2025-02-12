package array

func fourSumCount(nums1 []int, nums2 []int, nums3 []int, nums4 []int) int {
	n := len(nums1)
	m1, m2 := make(map[int]int), make(map[int]int)
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			m1[nums1[i]+nums2[j]]++
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			m2[nums3[i]+nums4[j]]++
		}
	}
	res := 0
	for k, v := range m1 {
		res += v * m2[-k]
	}
	return res
}
