package math

import "testing"

func Test_minimumDifference2(t *testing.T) {
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
		{"t1", args{[]int{1, 2, 4, 5}, 3}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumDifference2(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minimumDifference2() = %v, want %v", got, tt.want)
			}
		})
	}
}
