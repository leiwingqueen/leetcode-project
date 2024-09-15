package wc415

import "testing"

func Test_maxScore(t *testing.T) {
	type args struct {
		a []int
		b []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		// [3,2,5,6]
		//[2,-6,4,-5,-3,2,-7]
		{"t1", args{[]int{3, 2, 5, 6}, []int{2, -6, 4, -5, -3, 2, -7}}, 26},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxScore(tt.args.a, tt.args.b); got != tt.want {
				t.Errorf("maxScore() = %v, want %v", got, tt.want)
			}
		})
	}
}
