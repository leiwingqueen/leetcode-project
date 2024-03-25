package wc390

import "testing"

func Test_maximumLengthSubstring(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"bcbbbcba"}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumLengthSubstring(tt.args.s); got != tt.want {
				t.Errorf("maximumLengthSubstring() = %v, want %v", got, tt.want)
			}
		})
	}
}
