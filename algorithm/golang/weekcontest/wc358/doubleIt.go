package wc358

/**
 * Definition for singly-linked list.
 */
type ListNode struct {
	Val  int
	Next *ListNode
}

// 这样必然会溢出
func doubleIt(head *ListNode) *ListNode {
	num := 0
	cur := head
	for cur != nil {
		num = num*10 + cur.Val
		cur = cur.Next
	}
	num <<= 1
	if num == 0 {
		return &ListNode{Val: 0}
	}
	cur = nil
	for num > 0 {
		k := num % 10
		cur = &ListNode{k, cur}
		num /= 10
	}
	return cur
}

func doubleIt2(head *ListNode) *ListNode {
	var arr []int
	cur := head
	for cur != nil {
		arr = append(arr, cur.Val)
		cur = cur.Next
	}
	// 进位标记
	flag := false
	cur = nil
	for i := len(arr) - 1; i >= 0; i-- {
		num := arr[i]
		k := num << 1
		if flag {
			k++
		}
		if k < 10 {
			flag = false
			cur = &ListNode{k, cur}
		} else {
			flag = true
			cur = &ListNode{k - 10, cur}
		}
	}
	if flag {
		cur = &ListNode{1, cur}
	}
	return cur
}
