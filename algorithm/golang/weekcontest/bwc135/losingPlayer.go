package bwc135

func losingPlayer(x int, y int) string {
	for {
		if x < 1 || y < 4 {
			return "Bob"
		}
		x--
		y -= 4
		if x < 1 || y < 4 {
			return "Alice"
		}
		x--
		y -= 4
	}
}
