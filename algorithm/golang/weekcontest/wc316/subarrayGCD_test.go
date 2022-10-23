package wc316

import "testing"

func Test_subarrayGCD(t *testing.T) {
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
		{"t1", args{[]int{3, 12, 9, 6}, 3}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := subarrayGCD(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("subarrayGCD() = %v, want %v", got, tt.want)
			}
		})
	}
}
