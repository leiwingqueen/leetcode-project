package semoveSubfolders

import "strings"

// 你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
//
//如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的 子文件夹 。
//
//文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/' 后跟一个或者多个小写英文字母。
//
//例如，"/leetcode" 和 "/leetcode/problems" 都是有效的路径，而空字符串和 "/" 不是。
//
//
//示例 1：
//
//输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
//输出：["/a","/c/d","/c/f"]
//解释："/a/b" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
//示例 2：
//
//输入：folder = ["/a","/a/b/c","/a/b/d"]
//输出：["/a"]
//解释：文件夹 "/a/b/c" 和 "/a/b/d" 都会被删除，因为它们都是 "/a" 的子文件夹。
//示例 3：
//
//输入: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
//输出: ["/a/b/c","/a/b/ca","/a/b/d"]
//
//
//提示：
//
//1 <= folder.length <= 4 * 104
//2 <= folder[i].length <= 100
//folder[i] 只包含小写字母和 '/'
//folder[i] 总是以字符 '/' 起始
//每个文件夹名都是 唯一 的
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func removeSubfolders(folder []string) []string {
	tire := buildTire()
	for _, f := range folder {
		tire.add(f)
	}
	return tire.find()
}

type TireNode struct {
	child map[string]*TireNode
	end   bool
}

func buildTrieNode() *TireNode {
	return &TireNode{make(map[string]*TireNode), false}
}

type TireTree struct {
	root *TireNode
}

func buildTire() *TireTree {
	return &TireTree{buildTrieNode()}
}

func (t *TireTree) add(word string) {
	path := strings.Split(word, "/")
	cur := t.root
	for _, p := range path {
		if p == "" {
			continue
		}
		if _, exist := cur.child[p]; !exist {
			cur.child[p] = buildTrieNode()
		}
		cur = cur.child[p]
	}
	//特殊标记位，标识结束节点
	cur.end = true
}

func (t *TireTree) find() []string {
	res := make([]string, 0)
	var dfs func(node *TireNode, path string)
	dfs = func(node *TireNode, path string) {
		if node.end {
			res = append(res, path)
			return
		}
		for name, next := range node.child {
			path = path + "/" + name
			dfs(next, path)
			path = path[:len(path)-len(name)-1]
		}
	}
	dfs(t.root, "")
	return res
}
