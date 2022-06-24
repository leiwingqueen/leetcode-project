package tree

func findTilt(root *TreeNode) int {
	res := 0
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if root.Left == nil && root.Right == nil {
			return root.Val
		}
		left := dfs(root.Left)
		right := dfs(root.Right)
		res += abs(left - right)
		return left + right + root.Val
	}
	return res
}

func abs(x int) int {
	if x < 0 {
		x = -x
	}
	return x
}
