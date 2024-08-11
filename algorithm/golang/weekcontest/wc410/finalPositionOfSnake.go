package wc410

func finalPositionOfSnake(n int, commands []string) int {
	// "UP"、"RIGHT"、"DOWN" 和 "LEFT"
	dirs := make(map[string][]int)
	dirs["UP"] = []int{-1, 0}
	dirs["RIGHT"] = []int{0, 1}
	dirs["DOWN"] = []int{1, 0}
	dirs["LEFT"] = []int{0, -1}
	x, y := 0, 0
	for _, command := range commands {
		x += dirs[command][0]
		y += dirs[command][1]
	}
	return x*n + y
}
