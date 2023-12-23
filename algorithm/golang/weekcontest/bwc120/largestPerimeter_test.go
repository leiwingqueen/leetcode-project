package bwc120

import "testing"

func Test_largestPerimeter(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 12, 1, 2, 5, 50, 3}}, 12},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := largestPerimeter(tt.args.nums); got != tt.want {
				t.Errorf("largestPerimeter() = %v, want %v", got, tt.want)
			}
		})
	}
}
