package wc413

// 偶数行，列为偶是黑，奇数是白
// 奇数行，反之
func checkTwoChessboards(coordinate1 string, coordinate2 string) bool {
	getColor := func(c string) int {
		col := int(c[0]-'a') & 0b1
		row := int(c[1]-'0') & 0b1
		return row ^ col
	}
	return getColor(coordinate1) == getColor(coordinate2)
}
