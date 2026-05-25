package dp

import "testing"

func Test_canReach4(t *testing.T) {
	type args struct {
		s       string
		minJump int
		maxJump int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{"011010", 2, 3}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := canReach4(tt.args.s, tt.args.minJump, tt.args.maxJump); got != tt.want {
				t.Errorf("canReach4() = %v, want %v", got, tt.want)
			}
		})
	}
}
