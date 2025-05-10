package bwc155

func findCommonResponse(responses [][]string) string {
	n := len(responses)
	mp := make([]map[string]struct{}, n)
	for i, response := range responses {
		mp[i] = make(map[string]struct{})
		for _, r := range response {
			mp[i][r] = struct{}{}
		}
	}
	cnt := make(map[string]int)
	for _, item := range mp {
		for k := range item {
			cnt[k]++
		}
	}
	res := ""
	maxCnt := 0
	for k, v := range cnt {
		if v > maxCnt || (v == maxCnt && k < res) {
			maxCnt = v
			res = k
		}
	}
	return res
}
