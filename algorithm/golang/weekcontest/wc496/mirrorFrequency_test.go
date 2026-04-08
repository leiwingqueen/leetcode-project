package wc496

import "testing"

func Test_mirrorFrequency(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"ab1z9"}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := mirrorFrequency(tt.args.s); got != tt.want {
				t.Errorf("mirrorFrequency() = %v, want %v", got, tt.want)
			}
		})
	}
}
