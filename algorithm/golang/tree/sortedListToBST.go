package tree

// 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为
//平衡
// 二叉搜索树。
//
//
//
//示例 1:
//
//
//
//输入: head = [-10,-3,0,5,9]
//输出: [0,-3,9,-10,null,5]
//解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
//示例 2:
//
//输入: head = []
//输出: []
//
//
//提示:
//
//head 中的节点数在[0, 2 * 104] 范围内
//-105 <= Node.val <= 105

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

type ListNode struct {
	Val  int
	Next *ListNode
}

func sortedListToBST(head *ListNode) *TreeNode {
	var arr []int
	cur := head
	for cur != nil {
		arr = append(arr, cur.Val)
		cur = cur.Next
	}
	var buildTree func(l, r int) *TreeNode
	buildTree = func(l, r int) *TreeNode {
		if l > r {
			return nil
		}
		if l == r {
			return &TreeNode{arr[l], nil, nil}
		}
		mid := l + (r-l)/2
		return &TreeNode{arr[mid], buildTree(l, mid-1), buildTree(mid+1, r)}
	}
	return buildTree(0, len(arr)-1)
}
