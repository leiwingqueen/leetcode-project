package bwc110

import "testing"

func Test_minimumSeconds2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 1, 2}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumSeconds2(tt.args.nums); got != tt.want {
				t.Errorf("minimumSeconds2() = %v, want %v", got, tt.want)
			}
		})
	}
}
