package bwc86

import "testing"

func Test_maximumRows(t *testing.T) {
	type args struct {
		matrix    [][]int
		numSelect int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[0,0,0],[1,0,1],[0,1,1],[0,0,1]]
		//2
		{"t1", args{[][]int{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 0, 1}}, 2}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumRows(tt.args.matrix, tt.args.numSelect); got != tt.want {
				t.Errorf("maximumRows() = %v, want %v", got, tt.want)
			}
		})
	}
}
