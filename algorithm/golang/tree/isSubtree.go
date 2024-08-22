package tree

// 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
//
//二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
//
//
//
//示例 1：
//
//
//输入：root = [3,4,5,1,2], subRoot = [4,1,2]
//输出：true
//示例 2：
//
//
//输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
//输出：false
//
//
//提示：
//
//root 树上的节点数量范围是 [1, 2000]
//subRoot 树上的节点数量范围是 [1, 1000]
//-104 <= root.val <= 104
//-104 <= subRoot.val <= 104

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 无脑写法
func isSubtree(root *TreeNode, subRoot *TreeNode) bool {
	var checkEqual func(root *TreeNode, subRoot *TreeNode) bool
	checkEqual = func(root *TreeNode, subRoot *TreeNode) bool {
		if root == nil {
			return subRoot == nil
		}
		if subRoot == nil {
			return false
		}
		if root.Val != subRoot.Val {
			return false
		}
		return checkEqual(root.Left, subRoot.Left) && checkEqual(root.Right, subRoot.Right)
	}
	if root == nil {
		return subRoot == nil
	}
	if subRoot == nil {
		return true
	}
	if root.Val == subRoot.Val {
		if checkEqual(root, subRoot) {
			return true
		}
	}
	return isSubtree(root.Left, subRoot) || isSubtree(root.Right, subRoot)
}
