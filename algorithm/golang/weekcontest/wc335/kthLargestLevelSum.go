package wc335

import "sort"

/**
 * Definition for a binary tree node.
 */

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func kthLargestLevelSum(root *TreeNode, k int) int64 {
	queue := []*TreeNode{root}
	arr := []int64{int64(root.Val)}
	for len(queue) > 0 {
		size := len(queue)
		var sum int64
		for i := 0; i < size; i++ {
			node := queue[i]
			sum += int64(node.Val)
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
		}
		arr = append(arr, sum)
		queue = queue[size:]
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i] > arr[j]
	})
	if len(arr) < k {
		return -1
	} else {
		return arr[k-1]
	}
}
