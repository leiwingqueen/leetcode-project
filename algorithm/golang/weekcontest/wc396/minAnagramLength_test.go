package wc396

import "testing"

func Test_minAnagramLength(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abba"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minAnagramLength(tt.args.s); got != tt.want {
				t.Errorf("minAnagramLength() = %v, want %v", got, tt.want)
			}
		})
	}
}
