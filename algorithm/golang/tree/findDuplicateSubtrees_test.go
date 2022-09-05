package tree

import (
	"fmt"
	"testing"
)

func Test(t *testing.T) {
	var buildTree func(arr []int) *TreeNode
	buildTree = func(arr []int) *TreeNode {
		nodes := make([]*TreeNode, len(arr))
		for i, v := range arr {
			//<0表示空
			if v < 0 {
				continue
			}
			nodes[i] = &TreeNode{Val: v}
			if i > 0 {
				parent := (i - 1) / 2
				if i%2 == 1 {
					nodes[parent].Left = nodes[i]
				} else {
					nodes[parent].Right = nodes[i]
				}
			}
		}
		return nodes[0]
	}

	root := buildTree([]int{2, 1, 11, 11, -1, 1})
	subtrees := findDuplicateSubtrees(root)
	for _, tree := range subtrees {
		fmt.Printf("%d", tree.Val)
	}
}
