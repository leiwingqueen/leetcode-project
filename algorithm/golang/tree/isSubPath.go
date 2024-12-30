package tree

// 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
//
//如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
//
//一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
//
//
//
//示例 1：
//
//
//
//输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
//输出：true
//解释：树中蓝色的节点构成了与链表对应的子路径。
//示例 2：
//
//
//
//输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
//输出：true
//示例 3：
//
//输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
//输出：false
//解释：二叉树中不存在一一对应链表的路径。
//
//
//提示：
//
//二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
//链表包含的节点数目在 1 到 100 之间。
//二叉树包含的节点数目在 1 到 2500 之间。

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isSubPath(head *ListNode, root *TreeNode) bool {
	var dfs func(head *ListNode, root *TreeNode, leading bool) bool
	dfs = func(head *ListNode, root *TreeNode, leading bool) bool {
		if head == nil {
			return true
		}
		if root == nil {
			return false
		}
		if leading {
			if head.Val != root.Val {
				return false
			}
			return dfs(head.Next, root.Left, leading) || dfs(head.Next, root.Right, leading)
		} else {
			if head.Val == root.Val {
				if dfs(head.Next, root.Left, true) || dfs(head.Next, root.Right, true) {
					return true
				}
			}
			return dfs(head, root.Left, false) || dfs(head, root.Right, false)
		}
	}
	return dfs(head, root, false)
}
