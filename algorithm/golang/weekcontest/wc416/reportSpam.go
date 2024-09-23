package wc416

func reportSpam(message []string, bannedWords []string) bool {
	mp := make(map[string]bool)
	for _, word := range bannedWords {
		mp[word] = true
	}
	cnt := 0
	for _, word := range message {
		if mp[word] {
			cnt++
		}
		if cnt >= 2 {
			return true
		}
	}
	return false
}
