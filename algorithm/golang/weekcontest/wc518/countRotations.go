package wc518

func countRotations(s string, k int) int {
	arr := []byte(s)
	arr = append(arr, []byte(s)...)
	score := func(split int) int {
		cnt := 0
		for i := 0; i < len(s)-1; i++ {
			if arr[split+i] == arr[split+i+1] {
				cnt++
			}
		}
		return cnt
	}
	res := 0
	for i := 0; i < len(s); i++ {
		if score(i) == k {
			res++
		}
	}
	return res
}
