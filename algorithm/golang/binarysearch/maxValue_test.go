package binarysearch

import "testing"

func Test_maxValue(t *testing.T) {
	type args struct {
		n      int
		index  int
		maxSum int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{4, 2, 6}, 2},
		{"t2", args{6, 1, 10}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxValue2(tt.args.n, tt.args.index, tt.args.maxSum); got != tt.want {
				t.Errorf("maxValue() = %v, want %v", got, tt.want)
			}
		})
	}
}
