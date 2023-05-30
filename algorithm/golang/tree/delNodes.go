package tree

// 这写法是错误的
func delNodes(root *TreeNode, to_delete []int) []*TreeNode {
	delSet := make(map[int]struct{})
	for _, i := range to_delete {
		delSet[i] = struct{}{}
	}
	var dfs func(node *TreeNode) []*TreeNode
	dfs = func(node *TreeNode) []*TreeNode {
		if node == nil {
			return []*TreeNode{}
		}
		if _, exist := delSet[node.Val]; exist {
			l := dfs(node.Left)
			r := dfs(node.Right)
			return append(l, r...)
		} else {
			l := dfs(node.Left)
			r := dfs(node.Right)
			res := []*TreeNode{node}
			if len(l) > 0 {
				if node.Left == l[0] {
					res = append(res, l[1:]...)
				} else {
					res = append(res, l...)
				}
			}
			if len(r) > 0 {
				if node.Right == r[0] {
					res = append(res, r[1:]...)
				} else {
					res = append(res, r...)
				}
			}
			return res
		}
	}
	return dfs(root)
}

// 通过，但是效率还可以提高
func delNodes2(root *TreeNode, to_delete []int) []*TreeNode {
	delSet := make(map[int]struct{})
	for _, i := range to_delete {
		delSet[i] = struct{}{}
	}
	var dfs func(node, parent *TreeNode) []*TreeNode
	dfs = func(node, parent *TreeNode) []*TreeNode {
		if node == nil {
			return []*TreeNode{}
		}
		if _, exist := delSet[node.Val]; exist {
			if parent != nil {
				if parent.Left == node {
					parent.Left = nil
				} else {
					parent.Right = nil
				}
			}
			l := dfs(node.Left, node)
			r := dfs(node.Right, node)
			return append(l, r...)
		} else {
			l := dfs(node.Left, node)
			r := dfs(node.Right, node)
			res := []*TreeNode{node}
			if len(l) > 0 {
				if node.Left == l[0] {
					res = append(res, l[1:]...)
				} else {
					res = append(res, l...)
				}
			}
			if len(r) > 0 {
				if node.Right == r[0] {
					res = append(res, r[1:]...)
				} else {
					res = append(res, r...)
				}
			}
			return res
		}
	}
	return dfs(root, nil)
}

// 优化后的写法
func delNodes3(root *TreeNode, to_delete []int) []*TreeNode {
	delSet := make(map[int]struct{})
	for _, i := range to_delete {
		delSet[i] = struct{}{}
	}
	var res []*TreeNode
	var dfs func(node, parent *TreeNode)
	dfs = func(node, parent *TreeNode) {
		if node == nil {
			return
		}
		if _, exist := delSet[node.Val]; exist {
			// 删除节点node
			if parent != nil {
				if parent.Left == node {
					parent.Left = nil
				} else {
					parent.Right = nil
				}
			}
			dfs(node.Left, nil)
			dfs(node.Right, nil)
		} else {
			dfs(node.Left, node)
			dfs(node.Right, node)
			// 当父节点是nil，当前节点必然是根节点
			if parent == nil {
				res = append(res, node)
			}
		}
	}
	dfs(root, nil)
	return res
}
