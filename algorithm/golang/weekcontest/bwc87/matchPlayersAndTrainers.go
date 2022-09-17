package bwc87

import "sort"

func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	res := 0
	p1 := 0
	p2 := 0
	for p1 < len(players) && p2 < len(trainers) {
		if players[p1] <= trainers[p2] {
			p1++
			p2++
			res++
		} else {
			p2++
		}
	}
	return res
}
