package unionFind

import "testing"

func Test_shortestBridge2(t *testing.T) {
	type args struct {
		grid [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{0, 1}, {1, 0},
		}}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := shortestBridge2(tt.args.grid); got != tt.want {
				t.Errorf("shortestBridge2() = %v, want %v", got, tt.want)
			}
		})
	}
}
