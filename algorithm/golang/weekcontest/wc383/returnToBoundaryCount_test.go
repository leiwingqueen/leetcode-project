package wc383

import "testing"

func Test_returnToBoundaryCount(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{3, 2, -3, -4}}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := returnToBoundaryCount(tt.args.nums); got != tt.want {
				t.Errorf("returnToBoundaryCount() = %v, want %v", got, tt.want)
			}
		})
	}
}
