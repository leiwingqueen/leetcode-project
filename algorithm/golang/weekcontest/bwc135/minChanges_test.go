package bwc135

import "testing"

func Test_minChanges(t *testing.T) {
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
		{"t1", args{[]int{0, 1, 2, 3, 3, 6, 5, 4}, 6}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minChanges(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minChanges() = %v, want %v", got, tt.want)
			}
		})
	}
}
