package tree

//给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
//
//
//
//示例 1：
//
//
//输入: root = [5,3,6,2,4,null,7], k = 9
//输出: true
//示例 2：
//
//
//输入: root = [5,3,6,2,4,null,7], k = 28
//输出: false
//
//
//提示:
//
//二叉树的节点个数的范围是  [1, 104].
//-104 <= Node.val <= 104
//root 为二叉搜索树
//-105 <= k <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func findTarget(root *TreeNode, k int) bool {
	mp := make(map[int]int)
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		mp[node.Val]++
		dfs(node.Left)
		dfs(node.Right)
	}
	dfs(root)
	for i := range mp {
		if k-i == i {
			continue
		}
		_, ok := mp[k-i]
		if ok {
			return true
		}
	}
	return false
}

//稍微优化一下，不需要全部节点遍历一遍
func findTarget2(root *TreeNode, k int) bool {
	mp := make(map[int]struct{})
	var dfs func(node *TreeNode) bool
	dfs = func(node *TreeNode) bool {
		if node == nil {
			return false
		}
		if _, ok := mp[k-node.Val]; ok {
			return true
		}
		mp[node.Val] = struct{}{}
		if dfs(node.Left) {
			return true
		}
		return dfs(node.Right)
	}
	return dfs(root)
}

//TODO:双指针？
