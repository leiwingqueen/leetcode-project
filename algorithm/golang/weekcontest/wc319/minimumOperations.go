package wc319

import "sort"

// Definition for a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func minimumOperations(root *TreeNode) int {
	if root == nil {
		return 0
	}
	cnt := 0
	queue := []*TreeNode{root}
	for len(queue) > 0 {
		size := len(queue)
		mp := make(map[int]int)
		arr := make([]int, size)
		for i := 0; i < size; i++ {
			mp[queue[i].Val] = i
			arr[i] = queue[i].Val
			if queue[i].Left != nil {
				queue = append(queue, queue[i].Left)
			}
			if queue[i].Right != nil {
				queue = append(queue, queue[i].Right)
			}
		}
		cnt += change(arr)
		queue = queue[size:]
	}
	return cnt
}

func change(arr []int) int {
	n := len(arr)
	arr2 := make([]int, n)
	mp := make(map[int]int)
	for i := 0; i < n; i++ {
		arr2[i] = arr[i]
		mp[arr[i]] = i
	}
	sort.Ints(arr2)
	cnt := 0
	for i := 0; i < n; i++ {
		if arr2[i] != arr[i] {
			idx := mp[arr2[i]]
			mp[arr[i]] = idx
			arr[idx] = arr[i]
			cnt++
		}
	}
	return cnt
}
