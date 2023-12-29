package wc377

import "testing"

func Test_maximizeSquareArea(t *testing.T) {
	type args struct {
		m       int
		n       int
		hFences []int
		vFences []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{4, 3, []int{2, 3}, []int{2}}, 4},
		// m = 6, n = 7, hFences = [2], vFences = [4]
		{"t2", args{6, 7, []int{2}, []int{4}}, -1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximizeSquareArea2(tt.args.m, tt.args.n, tt.args.hFences, tt.args.vFences); got != tt.want {
				t.Errorf("maximizeSquareArea() = %v, want %v", got, tt.want)
			}
		})
	}
}
