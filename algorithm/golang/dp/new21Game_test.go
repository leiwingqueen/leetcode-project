package dp

import "testing"

func Test_new21Game(t *testing.T) {
	type args struct {
		n      int
		k      int
		maxPts int
	}
	tests := []struct {
		name string
		args args
		want float64
	}{
		// TODO: Add test cases.
		{"t1", args{6, 1, 10}, 0.6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := new21Game(tt.args.n, tt.args.k, tt.args.maxPts); got != tt.want {
				t.Errorf("new21Game() = %v, want %v", got, tt.want)
			}
		})
	}
}
