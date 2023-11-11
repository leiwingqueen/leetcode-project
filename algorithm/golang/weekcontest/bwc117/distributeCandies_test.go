package bwc117

import "testing"

func Test_distributeCandies3(t *testing.T) {
	type args struct {
		n     int
		limit int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{5, 2}, 3},
		{"t2", args{3, 3}, 10},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := distributeCandies3(tt.args.n, tt.args.limit); got != tt.want {
				t.Errorf("distributeCandies3() = %v, want %v", got, tt.want)
			}
		})
	}
}
