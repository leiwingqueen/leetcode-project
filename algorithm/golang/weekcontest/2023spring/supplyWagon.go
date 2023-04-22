package _023spring

import "math"

func supplyWagon(supplies []int) []int {
	n := len(supplies)
	for len(supplies) > n/2 {
		min := math.MaxInt
		idx := -1
		for i := 0; i < len(supplies)-1; i++ {
			if supplies[i]+supplies[i+1] < min {
				min = supplies[i] + supplies[i+1]
				idx = i
			}
		}
		supplies[idx] += supplies[idx+1]
		if idx+1 == len(supplies)-1 {
			supplies = supplies[:idx+1]
		} else {
			supplies = append(supplies[:idx+1], supplies[idx+2:]...)
		}
	}
	return supplies
}
