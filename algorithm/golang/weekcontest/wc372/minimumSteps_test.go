package wc372

import "testing"

func Test_minimumSteps(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{"100"}, 2},
		{"t2", args{"0111"}, 0},
		{"t3", args{"101"}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumSteps(tt.args.s); got != tt.want {
				t.Errorf("minimumSteps() = %v, want %v", got, tt.want)
			}
		})
	}
}
