package wc130

import "testing"

func Test_maxPointsInsideSquare(t *testing.T) {
	type args struct {
		points [][]int
		s      string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"
		{"t1", args{[][]int{
			{2, 2}, {-1, -2}, {-4, 4}, {-3, 1}, {3, -3},
		}, "abdca"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxPointsInsideSquare(tt.args.points, tt.args.s); got != tt.want {
				t.Errorf("maxPointsInsideSquare() = %v, want %v", got, tt.want)
			}
		})
	}
}
