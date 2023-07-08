package bwc108

import "testing"

func Test_alternatingSubarray(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{4, 5, 6}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := alternatingSubarray(tt.args.nums); got != tt.want {
				t.Errorf("alternatingSubarray() = %v, want %v", got, tt.want)
			}
		})
	}
}
