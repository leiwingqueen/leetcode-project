package tree

//最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。
//
//给你最大树的根节点 root 和一个整数 val 。
//
//就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
//
//如果 a 为空，返回 null 。
//否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
//root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
//root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
//返回 root 。
//请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
//
//假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
//
//返回 Construct(b) 。
//
//
//
//示例 1：
//
//
//
//输入：root = [4,1,3,null,null,2], val = 5
//输出：[5,4,null,1,3,null,null,2]
//解释：a = [1,4,2,3], b = [1,4,2,3,5]
//示例 2：
//
//
//输入：root = [5,2,4,null,1], val = 3
//输出：[5,2,4,null,1,null,3]
//解释：a = [2,1,5,4], b = [2,1,5,4,3]
//示例 3：
//
//
//输入：root = [5,2,3,null,1], val = 4
//输出：[5,2,4,null,1,3]
//解释：a = [2,1,5,3], b = [2,1,5,3,4]
//
//
//提示：
//
//树中节点数目在范围 [1, 100] 内
//1 <= Node.val <= 100
//树中的所有值 互不相同
//1 <= val <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-binary-tree-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 序列号+反序列化
func insertIntoMaxTree(root *TreeNode, val int) *TreeNode {
	var serialize func(node *TreeNode) []int
	var deserialize func(arr []int, l int, r int) *TreeNode
	// 前序遍历
	serialize = func(node *TreeNode) []int {
		if node == nil {
			return []int{}
		}
		res := make([]int, 0)
		res = append(res, serialize(node.Left)...)
		res = append(res, node.Val)
		res = append(res, serialize(node.Right)...)
		return res
	}
	deserialize = func(arr []int, l int, r int) *TreeNode {
		if l > r {
			return nil
		}
		if l == r {
			return &TreeNode{Val: arr[l]}
		}
		// find the biggest num
		idx := 0
		mx := -1
		for i := l; i <= r; i++ {
			if arr[i] > mx {
				mx = arr[i]
				idx = i
			}
		}
		node := &TreeNode{Val: arr[idx]}
		node.Left = deserialize(arr, l, idx-1)
		node.Right = deserialize(arr, idx+1, r)
		return node
	}
	arr := serialize(root)
	arr = append(arr, val)
	return deserialize(arr, 0, len(arr)-1)
}

// dfs
func insertIntoMaxTree2(root *TreeNode, val int) *TreeNode {
	if root == nil {
		return &TreeNode{Val: val}
	}
	if root.Val < val {
		node := &TreeNode{Val: val}
		node.Left = root
		return node
	} else {
		right := insertIntoMaxTree2(root.Right, val)
		root.Right = right
		return root
	}
}
