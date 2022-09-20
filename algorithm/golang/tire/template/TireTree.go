package template

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
