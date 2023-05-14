package wc345

import "testing"

func Test_countCompleteComponents(t *testing.T) {
	type args struct {
		n     int
		edges [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
		{"t1", args{6, [][]int{
			{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5},
		}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countCompleteComponents(tt.args.n, tt.args.edges); got != tt.want {
				t.Errorf("countCompleteComponents() = %v, want %v", got, tt.want)
			}
		})
	}
}
