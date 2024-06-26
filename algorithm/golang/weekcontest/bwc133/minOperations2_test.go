package bwc133

import "testing"

func Test_minOperations2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 1, 1, 0, 1}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minOperations3(tt.args.nums); got != tt.want {
				t.Errorf("minOperations2() = %v, want %v", got, tt.want)
			}
		})
	}
}
