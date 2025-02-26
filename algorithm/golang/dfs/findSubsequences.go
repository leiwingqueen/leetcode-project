package dfs

// Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.
//
// Example 1:
//
// Input: nums = [4,6,7,7]
// Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
// Example 2:
//
// Input: nums = [4,4,3,2,1]
// Output: [[4,4]]
//
// Constraints:
//
// 1 <= nums.length <= 15
// -100 <= nums[i] <= 100

// 这种题目的数据量其实就是直接回溯就好，这里有个问题，重复的子串如何剔除
func findSubsequences(nums []int) [][]int {
	n := len(nums)
	var res [][]int
	var dfs func(p1, p2 int, path []int)
	dfs = func(p1, p2 int, path []int) {
		if p1 == n {
			if p2 >= 2 {
				tmp := make([]int, p2)
				copy(tmp, path[:p2])
				res = append(res, tmp)
			}
			return
		}
		// 不选当前数字
		dfs(p1+1, p2, path)
		// 选择
		if p2 == 0 || nums[p1] >= path[p2-1] {
			path[p2] = nums[p1]
			dfs(p1+1, p2+1, path)
		}
	}
	dfs(0, 0, make([]int, n))
	return res
}

func findSubsequences2(nums []int) [][]int {
	tireTree := buildTire()
	n := len(nums)
	var dfs func(p1, p2 int, path []int)
	dfs = func(p1, p2 int, path []int) {
		if p1 == n {
			if p2 >= 2 {
				tireTree.add(path[:p2])
			}
			return
		}
		// 不选当前数字
		dfs(p1+1, p2, path)
		// 选择
		if p2 == 0 || nums[p1] >= path[p2-1] {
			path[p2] = nums[p1]
			dfs(p1+1, p2+1, path)
		}
	}
	dfs(0, 0, make([]int, n))
	return tireTree.getAll()
}

type TireNode struct {
	value int
	child map[int]*TireNode
	end   bool
}

func buildTrieNode(value int) *TireNode {
	return &TireNode{value, make(map[int]*TireNode), false}
}

type TireTree struct {
	root  *TireNode
	depth int
}

func buildTire() *TireTree {
	return &TireTree{buildTrieNode(0), 0}
}

func (t *TireTree) add(arr []int) {
	cur := t.root
	for _, num := range arr {
		if _, ok := cur.child[num]; !ok {
			cur.child[num] = buildTrieNode(num)
		}
		cur = cur.child[num]
	}
	cur.end = true
	t.depth = max(t.depth, len(arr))
}

func (t *TireTree) getAll() [][]int {
	var res [][]int
	var dfs func(node *TireNode, idx int, path []int)
	dfs = func(node *TireNode, idx int, path []int) {
		if node == nil {
			return
		}
		if node.end == true {
			tmp := make([]int, idx)
			copy(tmp, path)
			res = append(res, tmp)
		}
		path[idx] = node.value
		for _, child := range node.child {
			dfs(child, idx+1, path)
		}
	}
	for _, node := range t.root.child {
		dfs(node, 0, make([]int, t.depth))
	}
	return res
}
