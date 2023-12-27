package array

func isWinner(player1 []int, player2 []int) int {
	cal := func(player []int) int {
		sum := 0
		for i, v := range player {
			if (i >= 1 && player[i-1] == 10) || (i >= 2 && player[i-2] == 10) {
				sum += 2 * v
			} else {
				sum += v
			}
		}
		return sum
	}
	s1 := cal(player1)
	s2 := cal(player2)
	if s1 > s2 {
		return 1
	} else if s1 < s2 {
		return 2
	} else {
		return 0
	}
}
