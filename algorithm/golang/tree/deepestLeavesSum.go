package tree

//给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
//
//
//
//示例 1：
//
//
//
//输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
//输出：15
//示例 2：
//
//输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
//输出：19
//
//
//提示：
//
//树中节点数目在范围 [1, 104] 之间。
//1 <= Node.val <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/deepest-leaves-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func deepestLeavesSum(root *TreeNode) int {
	if root == nil {
		return 0
	}
	var queue []*TreeNode
	queue = append(queue, root)
	res := 0
	for len(queue) > 0 {
		sum := 0
		size := len(queue)
		for i := 0; i < size; i++ {
			node := queue[0]
			queue = queue[1:]
			sum += node.Val
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
		}
		res = sum
	}
	return res
}
