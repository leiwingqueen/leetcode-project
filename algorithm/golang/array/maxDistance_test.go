package array

import "testing"

func Test_maxDistance(t *testing.T) {
	type args struct {
		position []int
		m        int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 2, 3, 4, 7}, 3}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxDistance(tt.args.position, tt.args.m); got != tt.want {
				t.Errorf("maxDistance() = %v, want %v", got, tt.want)
			}
		})
	}
}
