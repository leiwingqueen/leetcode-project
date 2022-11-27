package bwc92

import "testing"

func Test_countPalindromes3(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"0000000"}, 21},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countPalindromes3(tt.args.s); got != tt.want {
				t.Errorf("countPalindromes3() = %v, want %v", got, tt.want)
			}
		})
	}
}
