package tree

// 给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
//
//提醒一下， 二叉搜索树 满足下列约束条件：
//
//节点的左子树仅包含键 小于 节点键的节点。
//节点的右子树仅包含键 大于 节点键的节点。
//左右子树也必须是二叉搜索树。
//
//
//示例 1：
//
//
//
//输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
//示例 2：
//
//输入：root = [0,null,1]
//输出：[1,null,1]
//
//
//提示：
//
//树中的节点数在 [1, 100] 范围内。
//0 <= Node.val <= 100
//树中的所有值均 不重复 。
//

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

// 错误，忽略了父节点的右子树的节点
func bstToGst(root *TreeNode) *TreeNode {
	var dfs func(node *TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		s1 := dfs(node.Left)
		s2 := dfs(node.Right)
		sum := s1 + s2 + node.Val
		node.Val += s2
		return sum
	}
	dfs(root)
	return root
}

// 先中序遍历，得到节点的排序，后缀和即为具体的答案
// 既然这么想，后序遍历不就能解决问题？用一个sum表示目前遍历的数字总和
func bstToGst2(root *TreeNode) *TreeNode {
	sum := 0
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		dfs(node.Right)
		sum += node.Val
		node.Val = sum
		dfs(node.Left)
	}
	dfs(root)
	return root
}
