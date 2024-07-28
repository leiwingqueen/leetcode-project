package wc408

import "testing"

func Test_nonSpecialCount(t *testing.T) {
	type args struct {
		l int
		r int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{5, 7}, 3},
		{"t2", args{16, 36}, 20},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := nonSpecialCount(tt.args.l, tt.args.r); got != tt.want {
				t.Errorf("nonSpecialCount() = %v, want %v", got, tt.want)
			}
		})
	}
}
