package array

import "testing"

func Test_isWinner(t *testing.T) {
	type args struct {
		player1 []int
		player2 []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{10, 2, 2, 3}, []int{3, 8, 4, 5}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isWinner(tt.args.player1, tt.args.player2); got != tt.want {
				t.Errorf("isWinner() = %v, want %v", got, tt.want)
			}
		})
	}
}
