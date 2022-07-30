package tree

import "math"

//给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
//
//请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
//
//
//
//示例 1：
//
//
//
//输入：root = [1,7,0,7,-8,null,null]
//输出：2
//解释：
//第 1 层各元素之和为 1，
//第 2 层各元素之和为 7 + 0 = 7，
//第 3 层各元素之和为 7 + -8 = -1，
//所以我们返回第 2 层的层号，它的层内元素之和最大。
//示例 2：
//
//输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
//输出：2
//
//
//提示：
//
//树中的节点数在 [1, 104]范围内
//-105 <= Node.val <= 105
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func maxLevelSum(root *TreeNode) int {
	queue := make([]*TreeNode, 0)
	queue = append(queue, root)
	res := -1
	mx := math.MinInt32
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		sum := 0
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
		depth++
		if sum > mx {
			res = depth
			mx = sum
		}
	}
	return res
}
