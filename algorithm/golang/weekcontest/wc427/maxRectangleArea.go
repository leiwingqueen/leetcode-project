package wc427

func maxRectangleArea(points [][]int) int {
	n := len(points)
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	type pair struct {
		x int
		y int
	}
	mp := make(map[pair]struct{})
	for _, point := range points {
		p := pair{point[0], point[1]}
		mp[p] = struct{}{}
	}
	res := -1
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			if x1 == x2 || y1 == y2 {
				continue
			}
			p1, p2 := pair{x1, y2}, pair{x2, y1}
			_, ok1 := mp[p1]
			_, ok2 := mp[p2]
			if ok1 && ok2 {
				flag := true
				for _, point := range points {
					if point[0] == x1 && point[1] == y2 || point[0] == x2 && point[1] == y1 ||
						point[0] == x1 && point[1] == y1 || point[0] == x2 && point[1] == y2 {
						continue
					}
					l, r := min(x1, x2), max(x1, x2)
					down, top := min(y1, y2), max(y1, y2)
					if point[0] >= l && point[0] <= r && point[1] >= down && point[1] <= top {
						flag = false
						break
					}
				}
				if flag {
					res = max(res, abs(x1-x2)*abs(y1-y2))
				}
			}
		}
	}
	return res
}
