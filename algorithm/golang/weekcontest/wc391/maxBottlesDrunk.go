package wc391

func maxBottlesDrunk(numBottles int, numExchange int) int {
	total := 0
	empty := 0
	for numBottles > 0 {
		total += numBottles
		empty += numBottles
		numBottles = 0
		if empty >= numExchange {
			numBottles++
			empty -= numExchange
			numExchange++
		}
	}
	return total
}
