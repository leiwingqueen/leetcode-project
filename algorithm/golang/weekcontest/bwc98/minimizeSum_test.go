package bwc98

import "testing"

func Test_minimizeSum(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{58, 42, 8, 75, 28}}, 30},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimizeSum(tt.args.nums); got != tt.want {
				t.Errorf("minimizeSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
