package wc314

import "testing"

func Test_numberOfPaths(t *testing.T) {
	type args struct {
		grid [][]int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[5,2,4],[3,0,5],[0,7,2]]
		//3
		{"t1", args{[][]int{{5, 2, 4}, {3, 0, 5}, {0, 7, 2}}, 3}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numberOfPaths(tt.args.grid, tt.args.k); got != tt.want {
				t.Errorf("numberOfPaths() = %v, want %v", got, tt.want)
			}
		})
	}
}
