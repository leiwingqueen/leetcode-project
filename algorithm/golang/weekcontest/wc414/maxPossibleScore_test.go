package wc414

import "testing"

func Test_maxPossibleScore2(t *testing.T) {
	type args struct {
		start []int
		d     int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{6, 0, 3}, 2}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxPossibleScore2(tt.args.start, tt.args.d); got != tt.want {
				t.Errorf("maxPossibleScore2() = %v, want %v", got, tt.want)
			}
		})
	}
}
