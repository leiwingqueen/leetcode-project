package bwc104

import "testing"

func Test_maximumOr(t *testing.T) {
	type args struct {
		nums []int
		k    int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		// [12,9]
		//1
		{"t1", args{[]int{12, 9}, 1}, 30},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumOr(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("maximumOr() = %v, want %v", got, tt.want)
			}
		})
	}
}
