package array

import "sort"

// There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
//
//You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
//
//Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
//
//
//
//Example 1:
//
//Input: trips = [[2,1,5],[3,3,7]], capacity = 4
//Output: false
//Example 2:
//
//Input: trips = [[2,1,5],[3,3,7]], capacity = 5
//Output: true
//
//
//Constraints:
//
//1 <= trips.length <= 1000
//trips[i].length == 3
//1 <= numPassengersi <= 100
//0 <= fromi < toi <= 1000
//1 <= capacity <= 105

// 数据量来说允许暴力
func carPooling(trips [][]int, capacity int) bool {
	cnt := make([]int, 1001)
	for _, trip := range trips {
		c, from, to := trip[0], trip[1], trip[2]
		for i := from; i < to; i++ {
			cnt[i] += c
			if cnt[i] > capacity {
				return false
			}
		}
	}
	return true
}

// 差分数组
func carPooling2(trips [][]int, capacity int) bool {
	cnt := make([]int, 1001)
	sort.Slice(trips, func(i, j int) bool {
		return trips[i][1] < trips[j][1]
	})
	for _, trip := range trips {
		c, from, to := trip[0], trip[1], trip[2]
		cnt[from] += c
		cnt[to] -= c
	}
	cur := 0
	for _, c := range cnt {
		cur += c
		if cur > capacity {
			return false
		}
	}
	return true
}
