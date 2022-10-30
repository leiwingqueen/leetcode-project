package wc317

/**
 * Definition for a binary tree node.
 */
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func treeQueries(root *TreeNode, queries []int) []int {
	cache := make(map[int]int)
	var cal1 func(node *TreeNode) int
	cal1 = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		res := 0
		defer func() {
			cache[node.Val] = res
		}()
		if node.Left == nil && node.Right == nil {
			res = 1
			return res
		}
		l := cal1(node.Left)
		r := cal1(node.Right)
		if l > r {
			res = l + 1
			return l + 1
		} else {
			res = r + 1
			return r + 1
		}
	}
	found := false
	var cal func(node *TreeNode, query int) int
	cal = func(node *TreeNode, query int) int {
		if node == nil {
			return 0
		}
		if found {
			return cache[node.Val]
		}
		if node.Val == query {
			found = true
			return 0
		}
		if node.Left == nil && node.Right == nil {
			return 1
		}
		if node.Left != nil && node.Left.Val == query {
			found = true
			return cal(node.Right, query) + 1
		}
		if node.Right != nil && node.Right.Val == query {
			found = true
			return cal(node.Left, query) + 1
		}
		l := cal(node.Left, query)
		r := cal(node.Right, query)
		if l > r {
			return l + 1
		} else {
			return r + 1
		}
	}
	cal1(root)
	res := make([]int, len(queries))
	for i, query := range queries {
		found = false
		res[i] = cal(root, query) - 1
	}
	return res
}
