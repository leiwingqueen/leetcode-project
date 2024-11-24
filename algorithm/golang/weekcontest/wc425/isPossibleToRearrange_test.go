package wc425

import "testing"

func Test_isPossibleToRearrange(t *testing.T) {
	type args struct {
		s string
		t string
		k int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{"abcd", "cdab", 2}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isPossibleToRearrange(tt.args.s, tt.args.t, tt.args.k); got != tt.want {
				t.Errorf("isPossibleToRearrange() = %v, want %v", got, tt.want)
			}
		})
	}
}
