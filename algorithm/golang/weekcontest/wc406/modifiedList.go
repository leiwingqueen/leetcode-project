package wc406

type ListNode struct {
	Val  int
	Next *ListNode
}

func modifiedList(nums []int, head *ListNode) *ListNode {
	remove := func(pre *ListNode) {
		cur := pre.Next
		pre.Next = cur.Next
	}
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
	}
	dummy := &ListNode{0, head}
	cur := dummy
	for cur.Next != nil {
		if mp[cur.Next.Val] {
			remove(cur)
		} else {
			cur = cur.Next
		}
	}
	return dummy.Next
}
