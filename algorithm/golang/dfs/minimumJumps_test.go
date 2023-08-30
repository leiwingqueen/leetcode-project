package dfs

import "testing"

func Test_minimumJumps(t *testing.T) {
	type args struct {
		forbidden []int
		a         int
		b         int
		x         int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [128,178,147,165,63,11,150,20,158,144,136]
		{"t1", args{[]int{128, 178, 147, 165, 63, 11, 150, 20, 158, 144, 136},
			61, 70, 35}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumJumps(tt.args.forbidden, tt.args.a, tt.args.b, tt.args.x); got != tt.want {
				t.Errorf("minimumJumps() = %v, want %v", got, tt.want)
			}
		})
	}
}
