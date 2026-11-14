package wc527

import "testing"

func Test_minCost(t *testing.T) {
	type args struct {
		n      int
		colors string
		edges  [][]int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{3, "RBR", [][]int{
			{0, 1}, {1, 2},
		}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minCost(tt.args.n, tt.args.colors, tt.args.edges); got != tt.want {
				t.Errorf("minCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
