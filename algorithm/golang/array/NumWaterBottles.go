package array

func numWaterBottles(numBottles int, numExchange int) int {
	return exchange(numBottles, 0, numExchange)
}

func exchange(full int, empty int, numExchange int) int {
	if full == 0 {
		return 0
	}
	ex := (full + empty) / numExchange
	mod := (full + empty) % numExchange
	return full + exchange(ex, mod, numExchange)
}
