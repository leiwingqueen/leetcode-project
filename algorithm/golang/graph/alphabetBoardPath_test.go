package graph

import "testing"

func Test_alphabetBoardPath(t *testing.T) {
	type args struct {
		target string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		{"t1", args{"leet"}, "DDR!UURRR!!DDD!"},
		{"t2", args{"z"}, "DDDDD!"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := alphabetBoardPath(tt.args.target); got != tt.want {
				t.Errorf("alphabetBoardPath() = %v, want %v", got, tt.want)
			}
		})
	}
}
