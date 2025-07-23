package wc459

import "testing"

func Test_countTrapezoids(t *testing.T) {
	type args struct {
		points [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{-73, -72}, {-1, -56}, {-92, 30}, {-57, -89}, {-19, -89}, {-35, 30}, {64, -72},
		}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countTrapezoids(tt.args.points); got != tt.want {
				t.Errorf("countTrapezoids() = %v, want %v", got, tt.want)
			}
		})
	}
}
