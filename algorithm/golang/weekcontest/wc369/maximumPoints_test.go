package wc369

import "testing"

func Test_maximumPoints(t *testing.T) {
	type args struct {
		edges [][]int
		coins []int
		k     int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{{0, 1}, {1, 2}, {2, 3}}, []int{10, 10, 3, 3}, 5}, 11},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumPoints(tt.args.edges, tt.args.coins, tt.args.k); got != tt.want {
				t.Errorf("maximumPoints() = %v, want %v", got, tt.want)
			}
		})
	}
}
