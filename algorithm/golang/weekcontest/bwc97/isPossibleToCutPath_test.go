package bwc97

import "testing"

func Test_isPossibleToCutPath(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		// [[1,1,1],[1,0,0],[1,1,1]]
		{"t1", args{[][]int{
			{1, 1, 1}, {1, 0, 0}, {1, 1, 1},
		}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isPossibleToCutPath(tt.args.grid); got != tt.want {
				t.Errorf("isPossibleToCutPath() = %v, want %v", got, tt.want)
			}
		})
	}
}
