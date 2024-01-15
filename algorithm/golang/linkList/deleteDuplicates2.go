package linkList

// 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
//
//
//
//示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
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
// 双指针
func deleteDuplicates2(head *ListNode) *ListNode {
	dummy := &ListNode{-101, nil}
	dummy.Next = head
	pre := dummy
	p1 := pre.Next
	for p1 != nil {
		p2 := p1
		for p2.Next != nil && p1.Val == p2.Next.Val {
			p2 = p2.Next
		}
		if p1 != p2 {
			// 需要删除节点
			pre.Next = p2.Next
			p2.Next = nil
			p1 = pre.Next
		} else {
			pre = p1
			p1 = p1.Next
		}
	}
	return dummy.Next
}
