package wc321

type ListNode struct {
	Val  int
	Next *ListNode
}

func removeNodes(head *ListNode) *ListNode {
	var arr []int
	node := head
	for node != nil {
		arr = append(arr, node.Val)
		node = node.Next
	}
	n := len(arr)
	delArr := make([]bool, n)
	mx := 0
	for i := n - 1; i >= 0; i-- {
		if arr[i] < mx {
			delArr[i] = true
			// fmt.Println(i)
		} else {
			mx = arr[i]
		}
	}
	dummy := &ListNode{0, head}
	pre := dummy
	cur := head
	idx := 0
	for cur != nil {
		if delArr[idx] {
			pre.Next = cur.Next
		} else {
			pre = cur
		}
		cur = cur.Next
		idx++
	}
	return dummy.Next
}
