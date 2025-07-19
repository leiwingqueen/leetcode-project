package wc458

import "testing"

func Test_maxLen(t *testing.T) {
	type args struct {
		n     int
		edges [][]int
		label string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{5, [][]int{
			{4, 3}, {2, 1}, {2, 4}, {0, 3}, {3, 1}, {3, 2},
		}, "bbeaa"}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxLen(tt.args.n, tt.args.edges, tt.args.label); got != tt.want {
				t.Errorf("maxLen() = %v, want %v", got, tt.want)
			}
		})
	}
}
