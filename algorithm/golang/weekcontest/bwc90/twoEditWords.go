package bwc90

func twoEditWords(queries []string, dictionary []string) []string {
	res := make([]string, 0)
	n := len(queries[0])
	var calDis func(s1 string, s2 string) bool
	calDis = func(s1 string, s2 string) bool {
		cnt := 0
		for i := 0; i < n; i++ {
			if s1[i] != s2[i] {
				cnt++
			}
			if cnt > 2 {
				return false
			}
		}
		return true
	}
	for _, query := range queries {
		for _, dict := range dictionary {
			if calDis(query, dict) {
				res = append(res, query)
				break
			}
		}
	}
	return res
}
