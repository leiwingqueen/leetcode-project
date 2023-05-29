package wc347

import "testing"

func Test_maxIncreasingCells(t *testing.T) {
	type args struct {
		mat [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[3,1],[3,4]]
		{"t1", args{[][]int{
			{3, 1},
			{3, 4},
		}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxIncreasingCells(tt.args.mat); got != tt.want {
				t.Errorf("maxIncreasingCells() = %v, want %v", got, tt.want)
			}
		})
	}
}
