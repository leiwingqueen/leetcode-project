package bwc121

import "math/bits"

func minOperations(nums []int, k int) int {
	xor := 0
	for _, num := range nums {
		xor ^= num
	}
	return bits.OnesCount(uint(xor ^ k))
}
