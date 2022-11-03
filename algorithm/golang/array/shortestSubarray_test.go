package array

import "testing"

func Test_shortestSubarray(t *testing.T) {
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
		// [2,-1,2]
		//3
		{"t1", args{[]int{2, -1, 2}, 3}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := shortestSubarray(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("shortestSubarray() = %v, want %v", got, tt.want)
			}
		})
	}
}
