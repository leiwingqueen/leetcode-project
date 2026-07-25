package wc511

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func countDominantNodes(root *TreeNode) int {
	res := 0
	var dfs func(node *TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		l := dfs(node.Left)
		r := dfs(node.Right)
		if node.Val >= l && node.Val >= r {
			res++
		}
		return max(l, r, node.Val)
	}
	dfs(root)
	return res
}
