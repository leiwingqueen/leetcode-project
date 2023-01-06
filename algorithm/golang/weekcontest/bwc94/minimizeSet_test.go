package bwc94

import "testing"

func Test_minimizeSet2(t *testing.T) {
	type args struct {
		divisor1   int
		divisor2   int
		uniqueCnt1 int
		uniqueCnt2 int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{2, 7, 1, 3}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimizeSet2(tt.args.divisor1, tt.args.divisor2, tt.args.uniqueCnt1, tt.args.uniqueCnt2); got != tt.want {
				t.Errorf("minimizeSet2() = %v, want %v", got, tt.want)
			}
		})
	}
}
