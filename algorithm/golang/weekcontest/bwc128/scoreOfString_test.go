package bwc128

import "testing"

func Test_scoreOfString(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"hello"}, 13},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := scoreOfString(tt.args.s); got != tt.want {
				t.Errorf("scoreOfString() = %v, want %v", got, tt.want)
			}
		})
	}
}
