package tree

// 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
//
//答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
//
//真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
//
//
//
//示例 1：
//
//
//输入：n = 7
//输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
//示例 2：
//
//输入：n = 3
//输出：[[0,0,0]]
//
//
//提示：
//
//1 <= n <= 20

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func allPossibleFBT(n int) []*TreeNode {
	var dfs func(k int) []*TreeNode
	dfs = func(k int) []*TreeNode {
		if k%2 == 0 {
			return []*TreeNode{}
		}
		if k == 1 {
			return []*TreeNode{{Val: 0}}
		}
		var res []*TreeNode
		for i := 1; i < k-1; i++ {
			left := dfs(i)
			right := dfs(k - i - 1)
			for _, l := range left {
				for _, r := range right {
					root := &TreeNode{Val: 0}
					root.Left = l
					root.Right = r
					res = append(res, root)
				}
			}
		}
		return res
	}
	return dfs(n)
}
