package tire

//设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
//
//实现 MagicDictionary 类：
//
//MagicDictionary() 初始化对象
//void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
//bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
//
//
//示例：
//
//输入
//["MagicDictionary", "buildDict", "search", "search", "search", "search"]
//[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
//输出
//[null, null, false, true, false, false]
//
//解释
//MagicDictionary magicDictionary = new MagicDictionary();
//magicDictionary.buildDict(["hello", "leetcode"]);
//magicDictionary.search("hello"); // 返回 False
//magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
//magicDictionary.search("hell"); // 返回 False
//magicDictionary.search("leetcoded"); // 返回 False
//
//
//提示：
//
//1 <= dictionary.length <= 100
//1 <= dictionary[i].length <= 100
//dictionary[i] 仅由小写英文字母组成
//dictionary 中的所有字符串 互不相同
//1 <= searchWord.length <= 100
//searchWord 仅由小写英文字母组成
//buildDict 仅在 search 之前调用一次
//最多调用 100 次 search
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/implement-magic-dictionary
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

type MagicDictionary struct {
	tree *TireTree
}

func Constructor() MagicDictionary {
	return MagicDictionary{buildTire()}
}

func (this *MagicDictionary) BuildDict(dictionary []string) {
	for _, word := range dictionary {
		this.tree.add(word)
	}
}

func (this *MagicDictionary) Search(searchWord string) bool {
	return this.tree.find(searchWord)
}

//多一位是为了特殊的标记位
var size = 27

// 前缀树
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
