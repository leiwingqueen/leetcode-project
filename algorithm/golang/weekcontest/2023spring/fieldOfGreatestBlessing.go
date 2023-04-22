package _023spring

func fieldOfGreatestBlessing(forceField [][]int) int {
	n := len(forceField)
	graph := make([][]int, n)
	connect := func(f1, f2 []int) bool {
		// x坐标判断
		x1 := float32(f1[0]) - float32(f1[2])/2
		x2 := float32(f1[0]) + float32(f1[2])/2
		x3 := float32(f2[0]) - float32(f2[2])/2
		x4 := float32(f2[0]) + float32(f2[2])/2
		if x2 < x3 || x1 > x4 {
			return false
		}
		y1 := float32(f1[1]) - float32(f1[2])/2
		y2 := float32(f1[1]) + float32(f1[2])/2
		y3 := float32(f2[1]) - float32(f2[2])/2
		y4 := float32(f2[1]) + float32(f2[2])/2
		if y2 < y3 || y1 > y4 {
			return false
		}
		return true
	}
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if connect(forceField[i], forceField[j]) {
				graph[i] = append(graph[i], j)
				graph[j] = append(graph[j], i)
			}
		}
	}
	return 0
}
