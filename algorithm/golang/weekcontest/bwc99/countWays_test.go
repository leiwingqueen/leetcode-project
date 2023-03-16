package bwc99

import "testing"

func Test_countWays(t *testing.T) {
	type args struct {
		ranges [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countWays(tt.args.ranges); got != tt.want {
				t.Errorf("countWays() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_countWays2(t *testing.T) {
	type args struct {
		ranges [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[6,10],[5,15]]
		{"t1", args{[][]int{{6, 10}, {5, 15}}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countWays2(tt.args.ranges); got != tt.want {
				t.Errorf("countWays2() = %v, want %v", got, tt.want)
			}
		})
	}
}
