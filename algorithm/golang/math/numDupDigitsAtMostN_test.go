package math

import "testing"

func Test_numDupDigitsAtMostN2(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{20}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numDupDigitsAtMostN2(tt.args.n); got != tt.want {
				t.Errorf("numDupDigitsAtMostN2() = %v, want %v", got, tt.want)
			}
		})
	}
}
