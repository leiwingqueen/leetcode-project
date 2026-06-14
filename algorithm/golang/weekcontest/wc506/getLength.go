package wc506

func getLength(nums []int) int {
	n := len(nums)
	check := func(mp map[int]int, reverseMap map[int]map[int]struct{}, maxCnt int) bool {
		if len(mp) == 1 || len(reverseMap) == 1 {
			return true
		}
		if len(reverseMap) > 2 {
			return false
		}
		other := 0
		for i := range reverseMap {
			if i != maxCnt {
				other = i
				break
			}
		}
		return maxCnt == 2*other
	}
	res := 0
	for i := 0; i < n; i++ {
		mp := make(map[int]int)
		reverseMap := make(map[int]map[int]struct{})
		maxCnt := 0
		for j := i; j < n; j++ {
			num := nums[j]
			mp[num]++
			cnt := mp[num]
			if _, ok := reverseMap[cnt]; !ok {
				reverseMap[cnt] = make(map[int]struct{})
			}
			reverseMap[cnt][num] = struct{}{}
			if cnt > 1 {
				delete(reverseMap[cnt-1], num)
			}
			maxCnt = max(maxCnt, cnt)
			if check(mp, reverseMap, maxCnt) {
				res = max(res, j-i+1)
			}
		}
	}
	return res
}
