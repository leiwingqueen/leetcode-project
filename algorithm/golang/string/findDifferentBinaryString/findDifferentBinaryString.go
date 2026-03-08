package findDifferentBinaryString

// 给你一个字符串数组 nums ，该数组由 n 个 互不相同 的二进制字符串组成，且每个字符串长度都是 n 。请你找出并返回一个长度为 n 且 没有出现 在 nums 中的二进制字符串。如果存在多种答案，只需返回 任意一个 即可。
//
// 示例 1：
//
// 输入：nums = ["01","10"]
// 输出："11"
// 解释："11" 没有出现在 nums 中。"00" 也是正确答案。
// 示例 2：
//
// 输入：nums = ["00","01"]
// 输出："11"
// 解释："11" 没有出现在 nums 中。"10" 也是正确答案。
// 示例 3：
//
// 输入：nums = ["111","011","001"]
// 输出："101"
// 解释："101" 没有出现在 nums 中。"000"、"010"、"100"、"110" 也是正确答案。
//
// 提示：
//
// n == nums.length
// 1 <= n <= 16
// nums[i].length == n
// nums[i] 为 '0' 或 '1'
// nums 中的所有字符串 互不相同

func findDifferentBinaryString(nums []string) string {
	n := len(nums)
	tree := buildTire()
	for _, num := range nums {
		tree.add(num)
	}
	var search func(path []byte, idx int, node *TireNode) (string, bool)
	search = func(path []byte, idx int, node *TireNode) (string, bool) {
		if idx == n {
			return "", false
		}
		for i := 0; i < 2; i++ {
			if node.child[i] == nil {
				path[idx] = byte('0' + i)
				// 全部填充'0'
				for j := idx + 1; j < n; j++ {
					path[j] = '0'
				}
				return string(path), true
			}
		}
		// 如果都已经存在，那么再尝试迭代遍历
		for i := 0; i < 2; i++ {
			path[idx] = byte('0' + i)
			if s, b := search(path, idx+1, node.child[i]); b {
				return s, b
			}
		}
		return "", false
	}
	s, _ := search(make([]byte, n), 0, tree.root)
	return s
}

// 前缀树模板
var size = 2

type TireNode struct {
	child []*TireNode
	end   bool
}

func buildTrieNode() *TireNode {
	return &TireNode{make([]*TireNode, size), false}
}

type TireTree struct {
	root *TireNode
}

func buildTire() *TireTree {
	return &TireTree{buildTrieNode()}
}

func (t *TireTree) add(word string) {
	cur := t.root
	for _, ch := range word {
		node := cur.child[ch-'0']
		if node == nil {
			cur.child[ch-'0'] = buildTrieNode()
		}
		cur = cur.child[ch-'0']
	}
	//特殊标记位，标识结束节点
	cur.end = true
}
