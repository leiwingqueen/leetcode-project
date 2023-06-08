package backtrace

import "testing"

func Test_tilingRectangle(t *testing.T) {
	type args struct {
		n int
		m int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{2, 2}, 1},
		{"t2", args{2, 3}, 3},
		{"t3", args{13, 11}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := tilingRectangle2(tt.args.n, tt.args.m); got != tt.want {
				t.Errorf("tilingRectangle() = %v, want %v", got, tt.want)
			}
		})
	}
}
