package wc316

import "testing"

func Test_minCost(t *testing.T) {
	type args struct {
		nums []int
		cost []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		// [2,2,2,2,2]
		//[4,2,8,1,3]
		{"t1", args{[]int{2, 2, 2, 2, 2}, []int{4, 2, 8, 1, 3}}, 0},
		{"t2", args{[]int{1, 3, 5, 2}, []int{4, 2, 8, 1, 3}}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minCost(tt.args.nums, tt.args.cost); got != tt.want {
				t.Errorf("minCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
