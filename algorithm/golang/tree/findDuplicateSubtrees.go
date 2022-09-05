package tree

import "fmt"

// 给定一棵二叉树 root，返回所有重复的子树。
//
//对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
//如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
//
//
//
//示例 1：
//
//
//
//输入：root = [1,2,3,4,null,2,4,null,null,4]
//输出：[[2,4],[4]]
//示例 2：
//
//
//
//输入：root = [2,1,1]
//输出：[[1]]
//示例 3：
//
//
//
//输入：root = [2,2,2,3,null,3,null]
//输出：[[2,3],[3]]
//
//
//提示：
//
//树中的结点数在[1,10^4]范围内。
//-200 <= Node.val <= 200
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-duplicate-subtrees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func findDuplicateSubtrees(root *TreeNode) []*TreeNode {
	visit := make(map[string]*TreeNode)
	cnt := make(map[string]int)
	var dfs func(node *TreeNode) string
	dfs = func(node *TreeNode) string {
		if node == nil {
			return ""
		}
		left := dfs(node.Left)
		right := dfs(node.Right)
		s := fmt.Sprintf("%d(%s)(%s)", node.Val, left, right)
		cnt[s]++
		if visit[s] == nil {
			visit[s] = node
		}
		return s
	}
	dfs(root)
	res := make([]*TreeNode, 0)
	for s, v := range cnt {
		if v > 1 {
			res = append(res, visit[s])
		}
	}
	return res
}
