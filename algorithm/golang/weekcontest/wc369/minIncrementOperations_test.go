package wc369

import "testing"

func Test_minIncrementOperations(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 1, 3, 3}, 5}, 2},
		{"t2", args{[]int{1, 3, 1, 6}, 6}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minIncrementOperations(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minIncrementOperations() = %v, want %v", got, tt.want)
			}
		})
	}
}
