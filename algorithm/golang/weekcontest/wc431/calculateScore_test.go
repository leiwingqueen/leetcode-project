package wc431

import "testing"

func Test_calculateScore(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{"aczzx"}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := calculateScore(tt.args.s); got != tt.want {
				t.Errorf("calculateScore() = %v, want %v", got, tt.want)
			}
		})
	}
}
