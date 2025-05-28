package bwc157

import "testing"

func Test_maxSubstrings(t *testing.T) {
	type args struct {
		word string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abcdeafdef"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxSubstrings(tt.args.word); got != tt.want {
				t.Errorf("maxSubstrings() = %v, want %v", got, tt.want)
			}
		})
	}
}
