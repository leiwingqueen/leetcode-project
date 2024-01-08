package bwc121

import "testing"

func Test_missingInteger(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{29, 30, 31, 32, 33, 34, 35, 36, 37}}, 297},
		{"t2", args{[]int{37, 1, 2, 9, 5, 8, 5, 2, 9, 4}}, 38},
		{"t3", args{[]int{1, 2, 3, 2, 5}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := missingInteger2(tt.args.nums); got != tt.want {
				t.Errorf("missingInteger() = %v, want %v", got, tt.want)
			}
		})
	}
}
