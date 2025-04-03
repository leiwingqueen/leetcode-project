package wc443

import "testing"

func Test_longestPalindrome(t *testing.T) {
	type args struct {
		s string
		t string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// "b"
		//"aaaa"
		{"t1", args{"b", "aaaa"}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := longestPalindrome2(tt.args.s, tt.args.t); got != tt.want {
				t.Errorf("longestPalindrome() = %v, want %v", got, tt.want)
			}
		})
	}
}
