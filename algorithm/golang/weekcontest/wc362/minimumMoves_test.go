package wc362

import "testing"

func Test_minimumMoves(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// grid = [[1,3,0],[1,0,0],[1,0,3]]
		{"t1", args{[][]int{
			{1, 3, 0}, {1, 0, 0}, {1, 0, 3},
		}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumMoves(tt.args.grid); got != tt.want {
				t.Errorf("minimumMoves() = %v, want %v", got, tt.want)
			}
		})
	}
}
