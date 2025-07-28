package wc460

import "testing"

func Test_maximumMedianSum(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 1, 3, 2, 1, 3}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumMedianSum(tt.args.nums); got != tt.want {
				t.Errorf("maximumMedianSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
