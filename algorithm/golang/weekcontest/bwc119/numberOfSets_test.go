package bwc119

import "testing"

func Test_numberOfSets(t *testing.T) {
	type args struct {
		n           int
		maxDistance int
		roads       [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{3, 12, [][]int{
			{1, 0, 11},
			{1, 0, 16},
			{0, 2, 13},
		}}, 5},
		{"t2", args{3, 5, [][]int{
			{0, 1, 20},
			{0, 1, 10},
			{1, 2, 2},
			{0, 2, 2},
		}}, 7},
		{"t3", args{1, 10, [][]int{}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numberOfSets(tt.args.n, tt.args.maxDistance, tt.args.roads); got != tt.want {
				t.Errorf("numberOfSets() = %v, want %v", got, tt.want)
			}
		})
	}
}
