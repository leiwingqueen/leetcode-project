package wc363

import "testing"

func Test_sumIndicesWithKSetBits(t *testing.T) {
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
		{"t1", args{[]int{5, 10, 1, 5, 2}, 1}, 13},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sumIndicesWithKSetBits(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("sumIndicesWithKSetBits() = %v, want %v", got, tt.want)
			}
		})
	}
}
