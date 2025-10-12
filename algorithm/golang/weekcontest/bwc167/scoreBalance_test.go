package bwc167

import "testing"

func Test_scoreBalance(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{"abdcd"}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := scoreBalance(tt.args.s); got != tt.want {
				t.Errorf("scoreBalance() = %v, want %v", got, tt.want)
			}
		})
	}
}
