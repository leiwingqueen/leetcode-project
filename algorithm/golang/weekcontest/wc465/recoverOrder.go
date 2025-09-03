package wc465

func recoverOrder(order []int, friends []int) []int {
	mp := make(map[int]struct{})
	for _, f := range friends {
		mp[f] = struct{}{}
	}
	var res []int
	for _, num := range order {
		if _, ok := mp[num]; ok {
			res = append(res, num)
		}
	}
	return res
}
