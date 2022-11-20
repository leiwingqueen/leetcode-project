package wc320

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func closestNodes(root *TreeNode, queries []int) [][]int {
	var arr []int
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		dfs(node.Left)
		arr = append(arr, node.Val)
		dfs(node.Right)
	}
	var search1 func(val int) int
	search1 = func(val int) int {
		if arr[0] > val {
			return -1
		}
		l := 0
		r := len(arr) - 1
		for l < r {
			mid := l + (r-l+1)/2
			if arr[mid] <= val {
				l = mid
			} else {
				r = mid - 1
			}
		}
		return arr[l]
	}
	var search2 func(val int) int
	search2 = func(val int) int {
		if arr[len(arr)-1] < val {
			return -1
		}
		l := 0
		r := len(arr) - 1
		for l < r {
			mid := l + (r-l)/2
			if arr[mid] >= val {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return arr[l]
	}
	dfs(root)
	res := make([][]int, len(queries))
	for i, query := range queries {
		res[i] = []int{search1(query), search2(query)}
	}
	return res
}
