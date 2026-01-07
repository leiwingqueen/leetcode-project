package tree

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 先尝试计算每个node的子树和，然后再计算最大乘积
// 这里其实有个数学思维，极大极小的问题。假设两颗子树的和越接近，那么积越大
func maxProduct(root *TreeNode) int {
	mod := 1_000_000_007
	var calSum func(node *TreeNode) int
	calSum = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		node.Val += calSum(node.Left) + calSum(node.Right)
		return node.Val
	}
	calSum(root)
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	minDiff := root.Val
	expectSubTree := 0
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		a := node.Val
		b := root.Val - node.Val
		if abs(a-b) < minDiff {
			minDiff = abs(a - b)
			expectSubTree = a
		}
		dfs(node.Left)
		dfs(node.Right)
	}
	dfs(root)
	return int(int64(expectSubTree) * int64(root.Val-expectSubTree) % int64(mod))
}
