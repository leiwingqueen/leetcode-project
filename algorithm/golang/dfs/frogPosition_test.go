package dfs

import "testing"

func Test_frogPosition(t *testing.T) {
	type args struct {
		n      int
		edges  [][]int
		t      int
		target int
	}
	tests := []struct {
		name string
		args args
		want float64
	}{
		// TODO: Add test cases.
		// 7
		//[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]
		//2
		//4
		{"t1", args{7, [][]int{
			{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5},
		}, 2, 4}, 0.16667},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := frogPosition(tt.args.n, tt.args.edges, tt.args.t, tt.args.target); got != tt.want {
				t.Errorf("frogPosition() = %v, want %v", got, tt.want)
			}
		})
	}
}
