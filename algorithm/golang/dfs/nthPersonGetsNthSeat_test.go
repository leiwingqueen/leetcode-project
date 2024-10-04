package dfs

import "testing"

func Test_nthPersonGetsNthSeat(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want float64
	}{
		// TODO: Add test cases.
		{"t1", args{2}, 0.5},
		{"t3", args{3}, 0.33},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := nthPersonGetsNthSeat(tt.args.n); got != tt.want {
				t.Errorf("nthPersonGetsNthSeat() = %v, want %v", got, tt.want)
			}
		})
	}
}
