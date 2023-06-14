package wc324

import "testing"

func Test_calPrimes(t *testing.T) {
	tests := []struct {
		name string
	}{
		// TODO: Add test cases.
		{"t1"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			calPrimes()
		})
	}
}

func Test_smallestValue(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{15}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := smallestValue(tt.args.n); got != tt.want {
				t.Errorf("smallestValue() = %v, want %v", got, tt.want)
			}
		})
	}
}
