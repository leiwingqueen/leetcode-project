package wc433

import "testing"

func Test_minMaxSums(t *testing.T) {
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
		{"t1", args{[]int{1, 2, 3}, 2}, 24},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minMaxSums2(tt.args.nums, tt.args.k); got != tt.want {
				t.Errorf("minMaxSums() = %v, want %v", got, tt.want)
			}
		})
	}
}
