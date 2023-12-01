package bwc118

import "testing"

func Test_maximizeSquareHoleArea(t *testing.T) {
	type args struct {
		n     int
		m     int
		hBars []int
		vBars []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{1, 1, []int{2}, []int{2}}, 4},
		// 2
		//1
		//[2,3]
		//[2]
		{"t2", args{2, 1, []int{2, 3}, []int{2}}, 4},
		{"t3", args{11, 6, []int{8, 9, 6}, []int{5, 3, 6, 4, 2, 7}}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximizeSquareHoleArea(tt.args.n, tt.args.m, tt.args.hBars, tt.args.vBars); got != tt.want {
				t.Errorf("maximizeSquareHoleArea() = %v, want %v", got, tt.want)
			}
		})
	}
}
