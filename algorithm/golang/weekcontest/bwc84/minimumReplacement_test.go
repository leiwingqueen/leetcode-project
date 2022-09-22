package bwc84

import "testing"

func Test_minimumReplacement(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{3, 9, 3}}, 2},
		{"t2", args{[]int{1, 2, 3, 4, 5}}, 0},
		{"t3", args{[]int{12, 9, 7, 6, 17, 19, 21}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumReplacement(tt.args.nums); got != tt.want {
				t.Errorf("minimumReplacement() = %v, want %v", got, tt.want)
			}
		})
	}
}
