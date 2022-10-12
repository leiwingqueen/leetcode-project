package linkList

func numComponents(head *ListNode, nums []int) int {
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
	}
	res := 0
	cur := head
	for cur != nil {
		for cur != nil && !mp[cur.Val] {
			cur = cur.Next
		}
		if cur == nil {
			return res
		}
		res++
		for cur != nil && mp[cur.Val] {
			cur = cur.Next
		}
	}
	return res
}
