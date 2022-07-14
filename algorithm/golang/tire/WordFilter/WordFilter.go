package WordFilter

import "fmt"

//import "leetcode-go/tire"

//745. 前缀和后缀搜索
//设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
//
//实现 WordFilter 类：
//
//WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
//f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
//
//
//示例：
//
//输入
//["WordFilter", "f"]
//[[["apple"]], ["a", "e"]]
//输出
//[null, 0]
//解释
//WordFilter wordFilter = new WordFilter(["apple"]);
//wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
//
//提示：
//
//1 <= words.length <= 104
//1 <= words[i].length <= 7
//1 <= pref.length, suff.length <= 7
//words[i]、pref 和 suff 仅由小写英文字母组成
//最多对函数 f 执行 104 次调用
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/prefix-and-suffix-search
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

type WordFilter struct {
	tree *TireTree
}

func Constructor(words []string) WordFilter {
	tireTree := buildTire()
	for idx, word := range words {
		for i := 0; i < len(word); i++ {
			nw := fmt.Sprintf("%s|%s", word[i:], word)
			tireTree.add(nw, idx)
		}
	}
	return WordFilter{tireTree}
}

func (this *WordFilter) F(pref string, suff string) int {
	search := fmt.Sprintf("%s|%s", suff, pref)
	return this.tree.find(search)
}

// 前缀树
type TireNode struct {
	end   bool
	child map[rune]*TireNode
	//对应的字符串的下标
	idx int
}

func buildTrieNode() *TireNode {
	return &TireNode{false, make(map[rune]*TireNode), -1}
}

type TireTree struct {
	root *TireNode
}

func buildTire() *TireTree {
	return &TireTree{buildTrieNode()}
}

func (t *TireTree) add(word string, idx int) {
	cur := t.root
	for _, ch := range word {
		node := cur.child[ch]
		if node == nil {
			cur.child[ch] = buildTrieNode()
		}
		cur = cur.child[ch]
	}
	//特殊标记位，标识结束节点
	cur.end = true
	cur.idx = idx
}

func (t *TireTree) find(word string) int {
	cur := t.root
	for _, ch := range word {
		if cur.child[ch] == nil {
			return -1
		}
		cur = cur.child[ch]
	}
	res := -1
	var dfs func(node *TireNode)
	dfs = func(node *TireNode) {
		if node == nil {
			return
		}
		if node.end && node.idx > res {
			res = node.idx
		}
		for _, next := range node.child {
			dfs(next)
		}
	}
	dfs(cur)
	return res
}
