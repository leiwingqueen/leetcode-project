package dp

import "testing"

func Test_minimizeMax(t *testing.T) {
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
		{"t1", args{[]int{10, 1, 2, 7, 1, 3}, 2}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimizeMax(tt.args.nums, tt.args.p); got != tt.want {
				t.Errorf("minimizeMax() = %v, want %v", got, tt.want)
			}
		})
	}
}
