package tree

// 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
//
//两个节点之间的路径长度 由它们之间的边数表示。
//
//
//
//示例 1:
//
//
//
//输入：root = [5,4,5,1,1,5]
//输出：2
//示例 2:
//
//
//
//输入：root = [1,4,5,4,4,5]
//输出：2
//
//
//提示:
//
//树的节点数的范围是 [0, 104]
//-1000 <= Node.val <= 1000
//树的深度将不超过 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/longest-univalue-path
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func longestUnivaluePath(root *TreeNode) int {
	if root == nil {
		return 0
	}
	// [包含根节点的最长路径，但是不能包含双边]
	res := 0
	var dfs func(node *TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		r := 1
		lMax := 1
		if node.Left != nil {
			left := dfs(node.Left)
			if node.Left.Val == node.Val {
				r += left
				lMax += left
			}
		}
		rMax := 1
		if node.Right != nil {
			right := dfs(node.Right)
			if node.Right.Val == node.Val {
				r += right
				rMax += right
			}
		}
		if r > res {
			res = r
		}
		return max(lMax, rMax)
	}
	dfs(root)
	return res - 1
}
