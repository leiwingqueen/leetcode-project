package dfs

import "testing"

func Test_countEval(t *testing.T) {
	type args struct {
		s      string
		result int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"1^0|0|1", 0}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countEval(tt.args.s, tt.args.result); got != tt.want {
				t.Errorf("countEval() = %v, want %v", got, tt.want)
			}
		})
	}
}
