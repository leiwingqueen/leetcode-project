package bfs

import "testing"

func Test_maximumDetonation(t *testing.T) {
	type args struct {
		bombs [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{2, 1, 3}, {6, 1, 4},
		}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumDetonation(tt.args.bombs); got != tt.want {
				t.Errorf("maximumDetonation() = %v, want %v", got, tt.want)
			}
		})
	}
}
