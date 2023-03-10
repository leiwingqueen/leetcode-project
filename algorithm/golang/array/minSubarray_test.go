package array

import "testing"

func Test_minSubarray2(t *testing.T) {
	type args struct {
		nums []int
		p    int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{3, 1, 4, 2}, 6}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minSubarray2(tt.args.nums, tt.args.p); got != tt.want {
				t.Errorf("minSubarray2() = %v, want %v", got, tt.want)
			}
		})
	}
}
