package dfs

import "testing"

func Test_minSkips(t *testing.T) {
	type args struct {
		dist        []int
		speed       int
		hoursBefore int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{[]int{1, 3, 2}, 4, 2}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minSkips(tt.args.dist, tt.args.speed, tt.args.hoursBefore); got != tt.want {
				t.Errorf("minSkips() = %v, want %v", got, tt.want)
			}
		})
	}
}
