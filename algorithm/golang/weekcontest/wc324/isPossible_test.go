package wc324

import "testing"

func Test_isPossible(t *testing.T) {
	type args struct {
		n     int
		edges [][]int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		// 5
		//[[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
		{"t1", args{5, [][]int{{1, 2}, {2, 3}, {3, 4}, {4, 2}, {1, 4}, {2, 5}}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isPossible(tt.args.n, tt.args.edges); got != tt.want {
				t.Errorf("isPossible() = %v, want %v", got, tt.want)
			}
		})
	}
}
