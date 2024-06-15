package bwc132

import "testing"

func Test_findWinningPlayer(t *testing.T) {
	type args struct {
		skills []int
		k      int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{4, 2, 6, 3, 9}, 2}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findWinningPlayer(tt.args.skills, tt.args.k); got != tt.want {
				t.Errorf("findWinningPlayer() = %v, want %v", got, tt.want)
			}
		})
	}
}
