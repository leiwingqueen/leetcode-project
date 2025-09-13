package wc466

import "testing"

func Test_bowlSubarrays2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"T1", args{[]int{2, 5, 3, 1, 4}}, 2},
		{"t2", args{[]int{1, 3, 2, 4}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := bowlSubarrays2(tt.args.nums); got != tt.want {
				t.Errorf("bowlSubarrays2() = %v, want %v", got, tt.want)
			}
		})
	}
}
