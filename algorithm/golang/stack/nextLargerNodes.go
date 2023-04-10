package stack

// 给定一个长度为 n 的链表 head
//
//对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
//
//返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
//
//示例 1：
//
//
//
//输入：head = [2,1,5]
//输出：[5,5,0]
//示例 2：
//
//
//
//输入：head = [2,7,4,3,5]
//输出：[7,0,5,5,0]
//提示：
//
//链表中节点数为 n
//1 <= n <= 104
//1 <= Node.val <= 109

/**
 * Definition for singly-linked list.
 */
type ListNode struct {
	Val  int
	Next *ListNode
}

func nextLargerNodes(head *ListNode) []int {
	stack := make([][]int, 0)
	node := head
	idx := 0
	var res []int
	for node != nil {
		for len(stack) > 0 && stack[len(stack)-1][1] < node.Val {
			pop := stack[len(stack)-1]
			res[pop[0]] = node.Val
			stack = stack[:len(stack)-1]
		}
		stack = append(stack, []int{idx, node.Val})
		res = append(res, 0)
		node = node.Next
		idx++
	}
	return res
}
