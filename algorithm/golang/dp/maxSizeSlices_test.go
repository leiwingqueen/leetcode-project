package dp

import "testing"

func Test_maxSizeSlices(t *testing.T) {
	type args struct {
		slices []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3, 4, 5, 6}}, 10},
		{"t2", args{[]int{8, 9, 8, 6, 1, 1}}, 16},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxSizeSlices(tt.args.slices); got != tt.want {
				t.Errorf("maxSizeSlices() = %v, want %v", got, tt.want)
			}
		})
	}
}
