package bwc121

import "testing"

func Test_minimumOperationsToMakeEqual(t *testing.T) {
	type args struct {
		x int
		y int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{26, 1}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumOperationsToMakeEqual(tt.args.x, tt.args.y); got != tt.want {
				t.Errorf("minimumOperationsToMakeEqual() = %v, want %v", got, tt.want)
			}
		})
	}
}
