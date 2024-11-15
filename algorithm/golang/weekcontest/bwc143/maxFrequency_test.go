package bwc143

import "testing"

func Test_maxFrequency(t *testing.T) {
	type args struct {
		nums          []int
		k             int
		numOperations int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"T1", args{[]int{5, 11, 20, 20}, 5, 1}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxFrequency2(tt.args.nums, tt.args.k, tt.args.numOperations); got != tt.want {
				t.Errorf("maxFrequency() = %v, want %v", got, tt.want)
			}
		})
	}
}
