package tree

// 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
//
//树的 最大宽度 是所有层中最大的 宽度 。
//
//每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
//
//题目数据保证答案将会在  32 位 带符号整数范围内。
//
//
//
//示例 1：
//
//
//输入：root = [1,3,2,5,3,null,9]
//输出：4
//解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
//示例 2：
//
//
//输入：root = [1,3,2,5,null,null,9,6,null,7]
//输出：7
//解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
//示例 3：
//
//
//输入：root = [1,3,2,5]
//输出：2
//解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
//
//
//提示：
//
//树中节点的数目范围是 [1, 3000]
//-100 <= Node.val <= 100
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-width-of-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 直接补全nil，会导致OOM
func widthOfBinaryTree(root *TreeNode) int {
	queue := []*TreeNode{root}
	width := 0
	for len(queue) > 0 {
		size := len(queue)
		i := 0
		for i < size && queue[0] == nil {
			queue = queue[1:]
			i++
		}
		if i == size {
			return width
		}
		l := i
		r := i
		for i < size {
			node := queue[0]
			queue = queue[1:]
			if node != nil {
				r = i
			}
			if node == nil {
				queue = append(queue, nil, nil)
			} else {
				queue = append(queue, node.Left, node.Right)
			}
			i++
		}
		if r-l+1 > width {
			width = r - l + 1
		}
	}
	return width
}

func widthOfBinaryTree2(root *TreeNode) int {
	root.Val = 1
	queue := []*TreeNode{root}
	width := 0
	for len(queue) > 0 {
		size := len(queue)
		w := queue[size-1].Val - queue[0].Val + 1
		for i := 0; i < size; i++ {
			node := queue[0]
			queue = queue[1:]
			if node.Left != nil {
				node.Left.Val = 2 * node.Val
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				node.Right.Val = 2*node.Val + 1
				queue = append(queue, node.Right)
			}
		}
		if w > width {
			width = w
		}
	}
	return width
}
