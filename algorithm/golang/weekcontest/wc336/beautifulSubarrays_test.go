package wc336

import "testing"

func Test_beautifulSubarrays(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{4, 3, 1, 2, 4}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := beautifulSubarrays(tt.args.nums); got != tt.want {
				t.Errorf("beautifulSubarrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
