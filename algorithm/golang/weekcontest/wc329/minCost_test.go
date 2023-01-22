package wc329

import "testing"

func Test_minCost(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [1,2,1,2,1,3,3]
		//2
		{"t1", args{[]int{1, 2, 1, 2, 1, 3, 3}, 2}, 8},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minCost(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
