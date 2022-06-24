package tree

import "strconv"

func tree2str(root *TreeNode) string {
	if root == nil {
		return ""
	}
	res := make([]byte, 0)
	res = append(res)
	bytes := []byte(strconv.Itoa(root.Val))
	res = append(res, bytes...)
	if root.Left == nil && root.Right == nil {
		return string(res)
	}
	left := []byte(tree2str(root.Left))
	res = append(res, '(')
	res = append(res, left...)
	res = append(res, ')')
	if root.Right != nil {
		res = append(res, '(')
		res = append(res, []byte(tree2str(root.Right))...)
		res = append(res, ')')
	}
	return string(res)
}
