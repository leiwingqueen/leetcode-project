package wc502

import "testing"

func Test_countKthRoots2(t *testing.T) {
	type args struct {
		l int
		r int
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{30, 64, 3}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countKthRoots2(tt.args.l, tt.args.r, tt.args.k); got != tt.want {
				t.Errorf("countKthRoots2() = %v, want %v", got, tt.want)
			}
		})
	}
}
