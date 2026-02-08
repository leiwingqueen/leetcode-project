package wc488

// 消消乐游戏，用栈来解决
func mergeAdjacent(nums []int) []int64 {
	var st []int64
	for _, num := range nums {
		insert := int64(num)
		for len(st) > 0 && st[len(st)-1] == insert {
			insert *= 2
			st = st[:len(st)-1]
		}
		st = append(st, insert)
	}
	return st
}
