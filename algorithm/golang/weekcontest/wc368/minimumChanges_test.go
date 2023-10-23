package wc368

import "testing"

func Test_minimumChanges(t *testing.T) {
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
		{"t1", args{"abcde", 2}, 2},
		{"t2", args{"abcac", 2}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumChanges(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("minimumChanges() = %v, want %v", got, tt.want)
			}
		})
	}
}
