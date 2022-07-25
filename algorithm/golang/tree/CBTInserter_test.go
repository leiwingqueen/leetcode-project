package tree

import (
	"fmt"
	"testing"
)

func Test1(t *testing.T) {
	root := &TreeNode{1, nil, nil}
	root.Left = &TreeNode{2, nil, nil}
	inserter := Constructor(root)
	r1 := inserter.Insert(3)
	fmt.Printf("%d\n", r1)
	r2 := inserter.Insert(4)
	fmt.Printf("%d\n", r2)
}
