package wc380

import "testing"

func Test_findMaximumNumber2(t *testing.T) {
	type args struct {
		k int64
		x int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{9, 1}, 6},
		{"t2", args{7, 2}, 9},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findMaximumNumber3(tt.args.k, tt.args.x); got != tt.want {
				t.Errorf("findMaximumNumber2() = %v, want %v", got, tt.want)
			}
		})
	}
}
