package wc313

import "testing"

func Test_deleteString(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abcabcdabc"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := deleteString(tt.args.s); got != tt.want {
				t.Errorf("deleteString() = %v, want %v", got, tt.want)
			}
		})
	}
}
