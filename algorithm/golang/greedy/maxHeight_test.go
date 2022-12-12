package greedy

import "testing"

func Test_maxHeight(t *testing.T) {
	type args struct {
		cuboids [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [[50,45,20],[95,37,53],[45,23,12]]
		{"t1", args{[][]int{{50, 45, 20}, {95, 37, 53}, {45, 23, 12}}}, 190},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxHeight(tt.args.cuboids); got != tt.want {
				t.Errorf("maxHeight() = %v, want %v", got, tt.want)
			}
		})
	}
}
