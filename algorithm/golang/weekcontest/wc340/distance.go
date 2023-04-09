package wc340

func distance(nums []int) []int64 {
	mp := make(map[int][]int)
	for i, num := range nums {
		mp[num] = append(mp[num], i)
	}
	arr := make([]int64, len(nums))
	for _, v := range mp {
		l := len(v)
		if l > 1 {
			prefix := make([]int64, l+1)
			for i, idx := range v {
				prefix[i+1] = prefix[i] + int64(idx)
			}
			for i, idx := range v {
				// 右边的距离和
				right := prefix[l] - prefix[i+1] - int64(l-i-1)*int64(idx)
				left := int64(i)*int64(idx) - prefix[i]
				arr[idx] = left + right
			}
		}
	}
	return arr
}
