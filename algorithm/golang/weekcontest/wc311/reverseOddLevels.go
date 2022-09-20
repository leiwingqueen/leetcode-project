package wc311

/**
 * Definition for a binary tree node.
 */
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func reverseOddLevels(root *TreeNode) *TreeNode {
	queue := make([]*TreeNode, 0)
	queue = append(queue, root)
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			node := queue[i]
			if node.Left != nil {
				queue = append(queue, node.Left)
				queue = append(queue, node.Right)
			}
		}
		if depth%2 == 1 {
			l := 0
			r := size - 1
			for l < r {
				queue[l].Val, queue[r].Val = queue[r].Val, queue[l].Val
				l++
				r--
			}
		}
		queue = queue[size:]
		depth++
	}
	return root
}
