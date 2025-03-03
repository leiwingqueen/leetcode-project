package wc439

import "testing"

func Test_longestPalindromicSubsequence(t *testing.T) {
	type args struct {
		s string
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abced", 2}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := longestPalindromicSubsequence(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("longestPalindromicSubsequence() = %v, want %v", got, tt.want)
			}
		})
	}
}
