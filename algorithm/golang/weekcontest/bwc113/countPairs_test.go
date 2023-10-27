package bwc113

import "testing"

func Test_countPairs2(t *testing.T) {
	type args struct {
		coordinates [][]int
		k           int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{{1, 2}, {4, 2}, {1, 3}, {5, 2}}, 5}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countPairs2(tt.args.coordinates, tt.args.k); got != tt.want {
				t.Errorf("countPairs2() = %v, want %v", got, tt.want)
			}
		})
	}
}
