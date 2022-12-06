package dfs

import "testing"

func Test_closestCost(t *testing.T) {
	type args struct {
		baseCosts    []int
		toppingCosts []int
		target       int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1}, []int{8, 10}, 10}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := closestCost(tt.args.baseCosts, tt.args.toppingCosts, tt.args.target); got != tt.want {
				t.Errorf("closestCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
