package wc419

import "sort"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func kthLargestPerfectSubtree(root *TreeNode, k int) int {
	var res []int
	var dfs func(node *TreeNode) (bool, int)
	dfs = func(node *TreeNode) (bool, int) {
		if node == nil {
			return true, 0
		}
		lb, l := dfs(node.Left)
		rb, r := dfs(node.Right)
		if lb && rb && l == r {
			res = append(res, (l<<1)+1)
			return true, (l << 1) + 1
		} else {
			return false, 0
		}
	}
	dfs(root)
	sort.Slice(res, func(i, j int) bool {
		return res[i] > res[j]
	})
	if len(res) < k {
		return -1
	} else {
		return res[k-1]
	}
}
