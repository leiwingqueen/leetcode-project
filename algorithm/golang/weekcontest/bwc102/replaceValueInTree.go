package bwc102

/**
 * Definition for a binary tree node.
 */
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func replaceValueInTree(root *TreeNode) *TreeNode {
	queue := [][]*TreeNode{
		{root, nil},
	}
	for len(queue) > 0 {
		size := len(queue)
		sum := 0
		mp := make(map[*TreeNode]int)
		for i := 0; i < size; i++ {
			node := queue[i]
			sum += node[0].Val
			mp[node[1]] += node[0].Val
			if node[0].Left != nil {
				queue = append(queue, []*TreeNode{node[0].Left, node[0]})
			}
			if node[0].Right != nil {
				queue = append(queue, []*TreeNode{node[0].Right, node[0]})
			}
		}
		for i := 0; i < size; i++ {
			node := queue[i]
			node[0].Val = sum - mp[node[1]]
		}
		queue = queue[size:]
	}
	return root
}
