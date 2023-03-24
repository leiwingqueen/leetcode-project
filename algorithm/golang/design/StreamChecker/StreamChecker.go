package StreamChecker

// 设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组 words 中的一个字符串。
//
//例如，words = ["abc", "xyz"] 且字符流中逐个依次加入 4 个字符 'a'、'x'、'y' 和 'z' ，你所设计的算法应当可以检测到 "axyz" 的后缀 "xyz" 与 words 中的字符串 "xyz" 匹配。
//
//按下述要求实现 StreamChecker 类：
//
//StreamChecker(String[] words) ：构造函数，用字符串数组 words 初始化数据结构。
//boolean query(char letter)：从字符流中接收一个新字符，如果字符流中的任一非空后缀能匹配 words 中的某一字符串，返回 true ；否则，返回 false。
//
//
//示例：
//
//输入：
//["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
//[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
//输出：
//[null, false, false, false, true, false, true, false, false, false, false, false, true]
//
//解释：
//StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
//streamChecker.query("a"); // 返回 False
//streamChecker.query("b"); // 返回 False
//streamChecker.query("c"); // 返回n False
//streamChecker.query("d"); // 返回 True ，因为 'cd' 在 words 中
//streamChecker.query("e"); // 返回 False
//streamChecker.query("f"); // 返回 True ，因为 'f' 在 words 中
//streamChecker.query("g"); // 返回 False
//streamChecker.query("h"); // 返回 False
//streamChecker.query("i"); // 返回 False
//streamChecker.query("j"); // 返回 False
//streamChecker.query("k"); // 返回 False
//streamChecker.query("l"); // 返回 True ，因为 'kl' 在 words 中
//
//
//提示：
//
//1 <= words.length <= 2000
//1 <= words[i].length <= 200
//words[i] 由小写英文字母组成
//letter 是一个小写英文字母
//最多调用查询 4 * 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/stream-of-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

type StreamChecker struct {
	stream   []byte
	tireTree *TireTree
}

func Constructor(words []string) StreamChecker {
	check := StreamChecker{make([]byte, 0), buildTire()}
	for _, word := range words {
		check.tireTree.add(word)
	}
	return check
}

func (this *StreamChecker) Query(letter byte) bool {
	this.stream = append(this.stream, letter)
	return this.tireTree.find(this.stream)
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
	n := len(word)
	for i := n - 1; i >= 0; i-- {
		ch := word[i]
		node := cur.child[ch-'a']
		if node == nil {
			cur.child[ch-'a'] = buildTrieNode()
		}
		cur = cur.child[ch-'a']
	}
	//特殊标记位，标识结束节点
	cur.child[size-1] = buildTrieNode()
}

func (t *TireTree) find(stream []byte) bool {
	var dfs func(node *TireNode, idx int) bool
	dfs = func(node *TireNode, idx int) bool {
		if node == nil {
			return false
		}
		if node.child[size-1] != nil {
			return true
		}
		if idx < 0 {
			return false
		}
		ch := stream[idx]
		return dfs(node.child[ch-'a'], idx-1)
	}
	return dfs(t.root, len(stream)-1)
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * obj := Constructor(words);
 * param_1 := obj.Query(letter);
 */
