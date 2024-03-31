package bwc127

import "testing"

func Test_minimumLevels(t *testing.T) {
	type args struct {
		possible []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 0, 1, 0}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumLevels(tt.args.possible); got != tt.want {
				t.Errorf("minimumLevels() = %v, want %v", got, tt.want)
			}
		})
	}
}
