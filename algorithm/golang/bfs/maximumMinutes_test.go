package bfs

import "testing"

func Test_maximumMinutes(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
		{"t1", args{[][]int{
			{0, 2, 0, 0, 0, 0, 0}, {0, 0, 0, 2, 2, 1, 0},
			{0, 2, 0, 0, 1, 2, 0}, {0, 0, 2, 2, 2, 0, 2},
			{0, 0, 0, 0, 0, 0, 0},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumMinutes(tt.args.grid); got != tt.want {
				t.Errorf("maximumMinutes() = %v, want %v", got, tt.want)
			}
		})
	}
}
