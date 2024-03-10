package wc388

// 给你一个数组 arr ，数组中有 n 个 非空 字符串。
//
//请你求出一个长度为 n 的字符串 answer ，满足：
//
//answer[i] 是 arr[i] 最短 的子字符串，且它不是 arr 中其他任何字符串的子字符串。如果有多个这样的子字符串存在，answer[i] 应该是它们中字典序最小的一个。如果不存在这样的子字符串，answer[i] 为空字符串。
//请你返回数组 answer 。
//
//
//
//示例 1：
//
//输入：arr = ["cab","ad","bad","c"]
//输出：["ab","","ba",""]
//解释：求解过程如下：
//- 对于字符串 "cab" ，最短没有在其他字符串中出现过的子字符串是 "ca" 或者 "ab" ，我们选择字典序更小的子字符串，也就是 "ab" 。
//- 对于字符串 "ad" ，不存在没有在其他字符串中出现过的子字符串。
//- 对于字符串 "bad" ，最短没有在其他字符串中出现过的子字符串是 "ba" 。
//- 对于字符串 "c" ，不存在没有在其他字符串中出现过的子字符串。
//示例 2：
//
//输入：arr = ["abc","bcd","abcd"]
//输出：["","","abcd"]
//解释：求解过程如下：
//- 对于字符串 "abc" ，不存在没有在其他字符串中出现过的子字符串。
//- 对于字符串 "bcd" ，不存在没有在其他字符串中出现过的子字符串。
//- 对于字符串 "abcd" ，最短没有在其他字符串中出现过的子字符串是 "abcd" 。
//
//
//提示：
//
//n == arr.length
//2 <= n <= 100
//1 <= arr[i].length <= 20
//arr[i] 只包含小写英文字母。

// 暴力
func shortestSubstrings(arr []string) []string {
	res := make([]string, len(arr))
	for i, s1 := range arr {
		// 构造tire tree
		tire := buildTire()
		for j, s2 := range arr {
			if i == j {
				continue
			}
			tire.add(s2)
		}
		// 检查每个字符开始的最短子串
		for k := 0; k < len(s1); k++ {
		}
	}
	return res
}

// 前缀树模板
var size = 27

type TireNode struct {
	child []*TireNode
}

func buildTrieNode() *TireNode {
	return &TireNode{make([]*TireNode, size)}
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
		node := cur.child[ch-'a']
		if node == nil {
			cur.child[ch-'a'] = buildTrieNode()
		}
		cur = cur.child[ch-'a']
	}
	//特殊标记位，标识结束节点
	cur.child[size-1] = buildTrieNode()
}

func (t *TireTree) find(word string) bool {
	var dfs func(node *TireNode, idx int, cnt int) bool
	dfs = func(node *TireNode, idx int, cnt int) bool {
		if node == nil {
			return false
		}
		if idx == len(word) {
			return cnt == 1 && node.child[size-1] != nil
		}
		ch := word[idx]
		if cnt == 1 {
			return dfs(node.child[ch-'a'], idx+1, cnt)
		} else {
			for i := 0; i < size-1; i++ {
				if i == int(ch-'a') {
					if sub := dfs(node.child[i], idx+1, cnt); sub {
						return true
					}
				} else {
					if sub := dfs(node.child[i], idx+1, cnt+1); sub {
						return true
					}
				}
			}
		}
		return false
	}
	return dfs(t.root, 0, 0)
}
