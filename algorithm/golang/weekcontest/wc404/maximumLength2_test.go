package wc404

import "testing"

func Test_maximumLength2(t *testing.T) {
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
		{"t1", args{[]int{1, 2, 3, 4, 5}, 2}, 5},
		{"t2", args{[]int{1, 2, 3, 10, 2}, 6}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumLength6(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("maximumLength2() = %v, want %v", got, tt.want)
			}
		})
	}
}
