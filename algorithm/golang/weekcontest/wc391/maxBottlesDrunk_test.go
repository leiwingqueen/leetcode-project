package wc391

import "testing"

func Test_maxBottlesDrunk(t *testing.T) {
	type args struct {
		numBottles  int
		numExchange int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{13, 6}, 15},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxBottlesDrunk(tt.args.numBottles, tt.args.numExchange); got != tt.want {
				t.Errorf("maxBottlesDrunk() = %v, want %v", got, tt.want)
			}
		})
	}
}
