package tree

func addOneRow(root *TreeNode, val int, depth int) *TreeNode {
	if depth == 1 {
		node := &TreeNode{val, nil, nil}
		node.Left = root
		return node
	} else {
		//queue := make([]*TreeNode, 0)
		//queue = append(queue, root)
		//可以优化成下面的写法
		queue := []*TreeNode{root}
		for i := 0; i < depth-2; i++ {
			size := len(queue)
			for j := 0; j < size; j++ {
				pop := queue[0]
				queue = queue[1:]
				if pop.Left != nil {
					queue = append(queue, pop.Left)
				}
				if pop.Right != nil {
					queue = append(queue, pop.Right)
				}
			}
		}
		//queue 保存的就是上一层的节点
		for _, node := range queue {
			l := node.Left
			r := node.Right
			node.Left = &TreeNode{val, l, nil}
			node.Right = &TreeNode{val, nil, r}
		}
		return root
	}
}
