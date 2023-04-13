package wc340

import "testing"

func Test_minimumVisitedCells2(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{3, 4, 2, 1}, {4, 2, 3, 1}, {2, 1, 0, 0}, {2, 4, 0, 0},
		}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumVisitedCells2(tt.args.grid); got != tt.want {
				t.Errorf("minimumVisitedCells2() = %v, want %v", got, tt.want)
			}
		})
	}
}
