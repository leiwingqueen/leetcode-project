package array

import (
	"sort"
	"strconv"
)

func findRelativeRanks(score []int) []string {
	res := make([]string, len(score))
	//保存对应的下标
	mp := make(map[int]int)
	for i, v := range score {
		mp[v] = i
	}
	sort.Slice(score, func(i, j int) bool {
		return score[i] > score[j]
	})
	medals := []string{"Gold Medal", "Silver Medal", "Bronze Medal"}
	for i := 0; i < len(score); i++ {
		//原来的下标
		idx := mp[score[i]]
		if i < len(medals) {
			res[idx] = medals[i]
		} else {
			res[idx] = strconv.Itoa(i + 1)
		}
	}
	return res
}
