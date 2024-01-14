package linkList

// 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
//
//
//
//示例 1：
//
//
//输入：head = [1,1,2]
//输出：[1,2]
//示例 2：
//
//
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
//
//
//提示：
//
//链表中节点数目在范围 [0, 300] 内
//-100 <= Node.val <= 100
//题目数据保证链表已经按升序 排列

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
