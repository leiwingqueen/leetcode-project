package array

import "testing"

func Test_flipChess(t *testing.T) {
	type args struct {
		chessboard []string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{chessboard: []string{"....X.", "....X.", "XOOO..", "......", "......"}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := flipChess2(tt.args.chessboard); got != tt.want {
				t.Errorf("flipChess() = %v, want %v", got, tt.want)
			}
		})
	}
}
