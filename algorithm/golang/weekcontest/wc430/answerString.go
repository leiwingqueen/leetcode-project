package wc430

func answerString(word string, numFriends int) string {
	if numFriends == 1 {
		return word
	}
	n := len(word)
	size := n - numFriends + 1
	res := word[0:size]
	for i := 1; i < n; i++ {
		s := word[i:min(n, i+size)]
		if s > res {
			res = s
		}
	}
	return res
}
