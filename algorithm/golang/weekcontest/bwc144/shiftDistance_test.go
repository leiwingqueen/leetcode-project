package bwc144

import "testing"

func Test_shiftDistance(t *testing.T) {
	type args struct {
		s            string
		t            string
		nextCost     []int
		previousCost []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{"abab", "baba",
			[]int{100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			[]int{1, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := shiftDistance(tt.args.s, tt.args.t, tt.args.nextCost, tt.args.previousCost); got != tt.want {
				t.Errorf("shiftDistance() = %v, want %v", got, tt.want)
			}
		})
	}
}
