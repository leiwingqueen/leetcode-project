package array

import (
	"sort"
	"strings"
)

//在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
//
//现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
//
//你需要输出替换之后的句子。
//
//
//
//示例 1：
//
//输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
//输出："the cat was rat by the bat"
//示例 2：
//
//输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
//输出："a a b c"
//
//
//提示：
//
//1 <= dictionary.length <= 1000
//1 <= dictionary[i].length <= 100
//dictionary[i] 仅由小写字母组成。
//1 <= sentence.length <= 10^6
//sentence 仅由小写字母和空格组成。
//sentence 中单词的总量在范围 [1, 1000] 内。
//sentence 中每个单词的长度在范围 [1, 1000] 内。
//sentence 中单词之间由一个空格隔开。
//sentence 没有前导或尾随空格。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/replace-words
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func replaceWords(dictionary []string, sentence string) string {
	sort.Slice(dictionary, func(i, j int) bool {
		return len(dictionary[i]) < len(dictionary[j])
	})
	words := strings.Split(sentence, " ")
	res := make([]byte, 0)
	for _, word := range words {
		replace := word
		for _, s := range dictionary {
			if strings.HasPrefix(word, s) {
				replace = s
				break
			}
		}
		if len(res) > 0 {
			res = append(res, ' ')
		}
		res = append(res, []byte(replace)...)
	}
	return string(res)
}

//由于题意字典的长度是远小于字典的数量的，可以尝试遍历字典的长度，而不是字典的数量
func replaceWords2(dictionary []string, sentence string) string {
	mxLen := 0
	mp := make(map[string]bool)
	for _, s := range dictionary {
		mp[s] = true
		if len(s) > mxLen {
			mxLen = len(s)
		}
	}
	words := strings.Split(sentence, " ")
	for i, word := range words {
		for j := 1; j <= mxLen && j <= len(word); j++ {
			if mp[word[:j]] {
				words[i] = word[:j]
				break
			}
		}
	}
	return strings.Join(words, " ")
}

//TODO:前缀树
