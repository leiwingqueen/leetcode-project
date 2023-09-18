package wc363

import "testing"

func Test_maxNumberOfAlloys(t *testing.T) {
	type args struct {
		n           int
		k           int
		budget      int
		composition [][]int
		stock       []int
		cost        []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{3, 2, 15, [][]int{
			{1, 1, 1},
			{1, 1, 10},
		}, []int{0, 0, 0}, []int{1, 2, 3}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxNumberOfAlloys(tt.args.n, tt.args.k, tt.args.budget, tt.args.composition, tt.args.stock, tt.args.cost); got != tt.want {
				t.Errorf("maxNumberOfAlloys() = %v, want %v", got, tt.want)
			}
		})
	}
}
