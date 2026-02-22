package wc489

func toggleLightBulbs(bulbs []int) []int {
	lights := make([]int, 100)
	for _, b := range bulbs {
		lights[b-1] ^= 0b01
	}
	var res []int
	for i, b := range lights {
		if b == 1 {
			res = append(res, i+1)
		}
	}
	return res
}
