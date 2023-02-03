package tree

import "testing"

func Test_btreeGameWinningMove(t *testing.T) {
	root := TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2}
	root.Right = &TreeNode{Val: 3}
	res := btreeGameWinningMove(&root, 3, 1)
	println(res)
}
