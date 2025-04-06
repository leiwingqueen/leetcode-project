package bwc153

import "testing"

func Test_reverseDegree(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abc"}, 148},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := reverseDegree(tt.args.s); got != tt.want {
				t.Errorf("reverseDegree() = %v, want %v", got, tt.want)
			}
		})
	}
}
