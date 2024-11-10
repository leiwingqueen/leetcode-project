package wc423

import "testing"

func Test_sumOfGoodSubsequences(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"T1", args{[]int{1, 2, 1}}, 14},
		{"T2", args{[]int{3, 4, 5}}, 40},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sumOfGoodSubsequences2(tt.args.nums); got != tt.want {
				t.Errorf("sumOfGoodSubsequences() = %v, want %v", got, tt.want)
			}
		})
	}
}
