package linkList

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteDuplicates(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	dummy := &ListNode{-201, nil}
	dummy.Next = head
	pre := dummy
	for pre.Next != nil {
		node := pre.Next
		if node.Val == pre.Val {
			// 删掉结点
			next := node.Next
			pre.Next = next
			node.Next = nil
		} else {
			pre = pre.Next
		}
	}
	return dummy.Next
}
