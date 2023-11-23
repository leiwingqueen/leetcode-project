package string

import (
	"strings"
)

// HTML entity parser is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.
//
//The special characters and their entities for HTML are:
//
//Quotation Mark: the entity is &quot; and symbol character is ".
//Single Quote Mark: the entity is &apos; and symbol character is '.
//Ampersand: the entity is &amp; and symbol character is &.
//Greater Than Sign: the entity is &gt; and symbol character is >.
//Less Than Sign: the entity is &lt; and symbol character is <.
//Slash: the entity is &frasl; and symbol character is /.
//Given the input text string to the HTML parser, you have to implement the entity parser.
//
//Return the text after replacing the entities by the special characters.
//
//
//
//Example 1:
//
//Input: text = "&amp; is an HTML entity but &ambassador; is not."
//Output: "& is an HTML entity but &ambassador; is not."
//Explanation: The parser will replace the &amp; entity by &
//Example 2:
//
//Input: text = "and I quote: &quot;...&quot;"
//Output: "and I quote: \"...\""
//
//
//Constraints:
//
//1 <= text.length <= 105
//The string may contain any possible characters out of all the 256 ASCII characters.

// 最搓写法，果然转义两次的场景是有问题的，所以应该用前缀树来解决？字符串匹配的问题
func entityParser(text string) string {
	mp := make(map[string]string)
	mp["&quot;"] = "\""
	mp["&apos;"] = "'"
	mp["&amp;"] = "&"
	mp["&gt;"] = ">"
	mp["&lt;"] = "<"
	mp["&frasl;"] = "/"
	for from, to := range mp {
		text = strings.ReplaceAll(text, from, to)
	}
	return text
}

// 贼复杂的写法
func entityParser2(text string) string {
	mp := make(map[string]string)
	mp["&quot;"] = "\""
	mp["&apos;"] = "'"
	mp["&amp;"] = "&"
	mp["&gt;"] = ">"
	mp["&lt;"] = "<"
	mp["&frasl;"] = "/"
	tire := buildTire()
	for k := range mp {
		tire.add(k[1 : len(k)-1])
	}
	var res []byte
	l, r := 0, 0
	n := len(text)
	for r < n {
		for l < n && text[l] != '&' {
			res = append(res, text[l])
			l++
		}
		if l == n {
			break
		}
		r = l + 1
		for r < n && text[r] != ';' && text[r] != '&' {
			r++
		}
		if r == n {
			res = append(res, text[l:]...)
			break
		}
		if text[r] == '&' {
			res = append(res, text[l:r]...)
			l = r
		} else {
			if find := tire.find(text[l+1 : r]); find {
				res = append(res, mp[text[l:r+1]]...)
			} else {
				res = append(res, text[l:r+1]...)
			}
			l = r + 1
		}
	}
	return string(res)
}

// 简化写法
func entityParser3(text string) string {
	mp := make(map[string]string)
	mp["&quot;"] = "\""
	mp["&apos;"] = "'"
	mp["&amp;"] = "&"
	mp["&gt;"] = ">"
	mp["&lt;"] = "<"
	mp["&frasl;"] = "/"
	var res []byte
	l, r := 0, 0
	n := len(text)
	for r < n {
		for l < n && text[l] != '&' {
			res = append(res, text[l])
			l++
		}
		if l == n {
			break
		}
		r = l + 1
		for r < n && text[r] != ';' && text[r] != '&' {
			r++
		}
		if r == n {
			res = append(res, text[l:]...)
			break
		}
		// 检查[l:r+1]是否匹配其中一个模式
		if v, ok := mp[text[l:r+1]]; ok {
			res = append(res, v...)
			l = r + 1
		} else {
			res = append(res, text[l:r]...)
			l = r
		}
	}
	return string(res)
}

type TireNode struct {
	child map[byte]*TireNode
}

func buildTrieNode() *TireNode {
	return &TireNode{make(map[byte]*TireNode)}
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
		node := cur.child[byte(ch)]
		if node == nil {
			cur.child[byte(ch)] = buildTrieNode()
		}
		cur = cur.child[byte(ch)]
	}
	//特殊标记位，标识结束节点
	cur.child[';'] = buildTrieNode()
}

func (t *TireTree) find(word string) bool {
	node := t.root
	for i := 0; i < len(word); i++ {
		ch := word[i]
		if node.child[ch] == nil {
			return false
		}
		node = node.child[ch]
	}
	return node.child[';'] != nil
}
