package dp

import "testing"

func Test_numberOfWays4(t *testing.T) {
	type args struct {
		n int
		x int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{10, 2}, 1},
		{"t2", args{8, 3}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numberOfWays4(tt.args.n, tt.args.x); got != tt.want {
				t.Errorf("numberOfWays4() = %v, want %v", got, tt.want)
			}
		})
	}
}
