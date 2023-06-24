package backtrace

import "testing"

func Test_getMaxGridHappiness(t *testing.T) {
	type args struct {
		m               int
		n               int
		introvertsCount int
		extrovertsCount int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// 2
		//3
		//1
		//2
		{"t1", args{2, 3, 1, 2}, 240},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := getMaxGridHappiness2(tt.args.m, tt.args.n, tt.args.introvertsCount, tt.args.extrovertsCount); got != tt.want {
				t.Errorf("getMaxGridHappiness() = %v, want %v", got, tt.want)
			}
		})
	}
}
