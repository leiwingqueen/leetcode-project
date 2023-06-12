package linkList

// 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
//
//删除完毕后，请你返回最终结果链表的头节点。
//
//
//
//你可以返回任何满足题目要求的答案。
//
//（注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
//
//示例 1：
//
//输入：head = [1,2,-3,3,1]
//输出：[3,1]
//提示：答案 [1,2,1] 也是正确的。
//示例 2：
//
//输入：head = [1,2,3,-3,4]
//输出：[1,2,4]
//示例 3：
//
//输入：head = [1,2,3,-3,-2]
//输出：[1]
//
//
//提示：
//
//给你的链表中可能有 1 到 1000 个节点。
//对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000.
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func removeZeroSumSublists(head *ListNode) *ListNode {
	var st []int
	mp := make(map[int]int)
	mp[0] = 1
	sum := 0
	for cur := head; cur != nil; cur = cur.Next {
		sum += cur.Val
		if v, exist := mp[sum]; !exist || v <= 0 {
			st = append(st, cur.Val)
			mp[sum]++
		} else {
			s := cur.Val
			sum -= cur.Val
			for s != 0 {
				top := st[len(st)-1]
				st = st[:len(st)-1]
				s += top
				mp[sum]--
				sum -= top
			}
		}
	}
	dummy := &ListNode{Val: 0}
	cur := dummy
	for _, v := range st {
		cur.Next = &ListNode{Val: v}
		cur = cur.Next
	}
	return dummy.Next
}
