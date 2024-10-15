package wc419

import "testing"

func Test_countWinningSequences(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"FFF"}, 3},
		{"t2", args{"FWEFW"}, 18},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countWinningSequences(tt.args.s); got != tt.want {
				t.Errorf("countWinningSequences() = %v, want %v", got, tt.want)
			}
		})
	}
}
