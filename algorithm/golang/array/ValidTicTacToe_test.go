package array

import "testing"

func Test_validTicTacToe2(t *testing.T) {
	type args struct {
		board []string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		{"test1", args{[]string{"XXX", "OOX", "OOX"}}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := validTicTacToe2(tt.args.board); got != tt.want {
				t.Errorf("validTicTacToe2() = %v, want %v", got, tt.want)
			}
		})
	}
}
