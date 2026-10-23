package math

import "testing"

func Test_sortPermutation(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 1, 3, 2, 4}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sortPermutation(tt.args.nums); got != tt.want {
				t.Errorf("sortPermutation() = %v, want %v", got, tt.want)
			}
		})
	}
}
