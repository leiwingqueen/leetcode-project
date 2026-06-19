package linkList

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

// 快慢指针
// 慢指针每次移动一格，快指针每次移动两格，这样就能找到要删除的点
func deleteMiddle(head *ListNode) *ListNode {
	slow, fast := head, head
	dummy := &ListNode{0, head}
	pre := dummy
	// 找到中间的节点
	for fast.Next != nil {
		pre = slow
		slow = slow.Next
		fast = fast.Next
		if fast.Next != nil {
			fast = fast.Next
		}
	}
	// 开始处理删除
	pre.Next = slow.Next
	slow.Next = nil
	return dummy.Next
}
