package greedy

// leetcode submit region begin(Prohibit modification and deletion)
func wateringPlants(plants []int, capacity int) int {
	n := len(plants)
	res := 0
	c := capacity
	pos := -1
	idx := 0
	for idx < n {
		if c >= plants[idx] {
			c -= plants[idx]
			res += idx - pos
			pos++
			idx++
		} else {
			// 返回取水
			c = capacity
			res += 2 * (pos + 1)
		}
	}
	return res
}
