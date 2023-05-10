package math

import "testing"

func Test_smallestRepunitDivByK(t *testing.T) {
	type args struct {
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{5}, -1},
		{"t2", args{7}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := smallestRepunitDivByK(tt.args.k); got != tt.want {
				t.Errorf("smallestRepunitDivByK() = %v, want %v", got, tt.want)
			}
		})
	}
}
