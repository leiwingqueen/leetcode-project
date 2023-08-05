package bwc110

/**
 * Definition for singly-linked list.
 */
type ListNode struct {
	Val  int
	Next *ListNode
}

func insertGreatestCommonDivisors(head *ListNode) *ListNode {
	var gcd func(a int, b int) int
	gcd = func(a int, b int) int {
		if b == 0 {
			return a
		} else {
			return gcd(b, a%b)
		}
	}
	cur := head
	for cur != nil && cur.Next != nil {
		v := gcd(cur.Val, cur.Next.Val)
		tmp := ListNode{Val: v}
		next := cur.Next
		cur.Next = &tmp
		tmp.Next = next
		cur = next
	}
	return head
}
