package tree

// 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
//
//如果存在多个答案，您可以返回其中 任何 一个。
//
//
//
//示例 1：
//
//
//
//输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
//输出：[1,2,3,4,5,6,7]
//示例 2:
//
//输入: preorder = [1], postorder = [1]
//输出: [1]
//
//
//提示：
//
//1 <= preorder.length <= 30
//1 <= preorder[i] <= preorder.length
//preorder 中所有值都 不同
//postorder.length == preorder.length
//1 <= postorder[i] <= postorder.length
//postorder 中所有值都 不同
//保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	// 考虑到需要快速定位对应值的下标，我们可以建立一个map来快速查找
	mp1, mp2 := make(map[int]int), make(map[int]int)
	for i, num := range preorder {
		mp1[num] = i
	}
	for i, num := range postorder {
		mp2[num] = i
	}
	var dfs func(l1, r1, l2, r2 int) *TreeNode
	dfs = func(l1, r1, l2, r2 int) *TreeNode {
		if l1 > r1 || l2 > r2 {
			return nil
		}
		if l1 == r1 {
			return &TreeNode{preorder[l1], nil, nil}
		}
		// 先确认根节点
		root := &TreeNode{preorder[l1], nil, nil}
		// 找到前序遍历的第一个点
		v := preorder[l1+1]
		// 找到这个点在后序遍历的位置
		i := mp2[v]
		// 假设这个点不是最后一个点，那么这个点一定为左子树的根
		if i < r2-1 {
			// right child node val
			rightVal := postorder[r2-1]
			idx := mp1[rightVal]
			leftNode := dfs(l1+1, idx-1, l2, i)
			rightNode := dfs(idx, r1, i+1, r2-1)
			root.Left = leftNode
			root.Right = rightNode
		} else {
			// 只有一个右子树 or 左子树。这里设置left node和right node都是可以的
			rightNode := dfs(l1+1, r1, l2, r2-1)
			root.Right = rightNode
		}
		return root
	}
	n := len(preorder)
	return dfs(0, n-1, 0, n-1)
}
