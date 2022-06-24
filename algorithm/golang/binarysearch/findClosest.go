package binarysearch

//有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
//
//示例：
//
//输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
//输出：1
//提示：
//
//words.length <= 100000
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-closest-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func findClosest(words []string, word1 string, word2 string) int {
	//滑动窗口检测
	check := func(distance int) bool {
		l := 0
		r := 0
		cnt1 := 0
		cnt2 := 0
		for r < len(words) {
			if r-l >= distance {
				if words[l] == word1 {
					cnt1--
				}
				if words[l] == word2 {
					cnt2--
				}
				l++
			}
			if words[r] == word1 {
				cnt1++
			}
			if words[r] == word2 {
				cnt2++
			}
			if cnt1 > 0 && cnt2 > 0 {
				return true
			}
			r++
		}
		return false
	}
	//二分查找
	l := 1
	r := len(words)
	for l < r {
		mid := l + (r-l)/2
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l - 1
}
