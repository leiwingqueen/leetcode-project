package bwc138

import "testing"

func Test_stringHash(t *testing.T) {
	type args struct {
		s string
		k int
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		// s = "abcd", k = 2
		{"t1", args{"abcd", 2}, "bf"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := stringHash(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("stringHash() = %v, want %v", got, tt.want)
			}
		})
	}
}
