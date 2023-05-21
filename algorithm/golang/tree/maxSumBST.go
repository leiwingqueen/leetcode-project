package tree

// 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
//
//二叉搜索树的定义如下：
//
//任意节点的左子树中的键值都 小于 此节点的键值。
//任意节点的右子树中的键值都 大于 此节点的键值。
//任意节点的左子树和右子树都是二叉搜索树。
//
//
//示例 1：
//
//
//
//输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//输出：20
//解释：键值为 3 的子树是和最大的二叉搜索树。
//示例 2：
//
//
//
//输入：root = [4,3,null,1,2]
//输出：2
//解释：键值为 2 的单节点子树是和最大的二叉搜索树。
//示例 3：
//
//输入：root = [-4,-2,-5]
//输出：0
//解释：所有节点键值都为负数，和最大的二叉搜索树为空。
//示例 4：
//
//输入：root = [2,1,3]
//输出：6
//示例 5：
//
//输入：root = [5,4,8,3,null,6,3]
//输出：7
//
//
//提示：
//
//每棵树有 1 到 40000 个节点。
//每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func maxSumBST(root *TreeNode) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	min := func(a, b int) int {
		if a > b {
			return b
		} else {
			return a
		}
	}
	res := 0
	// 是否二叉搜索树,最小值,最大值，子树的和
	var dfs func(node *TreeNode) (bool, int, int, int)
	dfs = func(node *TreeNode) (bool, int, int, int) {
		t1, t2, t3, t4 := false, 0, 0, 0
		defer func() {
			if t1 && t4 > res {
				res = t4
			}
		}()
		if node == nil {
			t1, t2, t3, t4 = true, 0, 0, 0
			// return true, 0, 0, 0
		} else if node.Left == nil && node.Right == nil {
			t1, t2, t3, t4 = true, node.Val, node.Val, node.Val
		} else if node.Left == nil {
			r1, r2, r3, r4 := dfs(node.Right)
			if r1 && r2 > node.Val {
				t1, t2, t3, t4 = true, node.Val, r3, node.Val+r4
			} else {
				t1, t2, t3, t4 = false, r2, max(node.Val, r3), node.Val+r4
			}
		} else if node.Right == nil {
			l1, l2, l3, l4 := dfs(node.Left)
			if l1 && l3 < node.Val {
				t1, t2, t3, t4 = true, l2, node.Val, node.Val+l4
			} else {
				t1, t2, t3, t4 = false, min(node.Val, l2), l3, node.Val+l4
			}
		} else {
			l1, l2, l3, l4 := dfs(node.Left)
			r1, r2, r3, r4 := dfs(node.Right)
			if l1 && r1 && l3 < node.Val && r2 > node.Val {
				t1, t2, t3, t4 = true, l2, r3, node.Val+l4+r4
			} else {
				t1, t2, t3, t4 = false, min(node.Val, min(l2, r2)), max(node.Val, max(l3, r3)), node.Val+l4+r4
			}
		}
		return t1, t2, t3, t4
	}
	dfs(root)
	return res
}
