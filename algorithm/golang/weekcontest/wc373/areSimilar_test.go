package wc373

import "testing"

func Test_areSimilar(t *testing.T) {
	type args struct {
		mat [][]int
		k   int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{2, 2}, {2, 2},
		}, 3}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := areSimilar(tt.args.mat, tt.args.k); got != tt.want {
				t.Errorf("areSimilar() = %v, want %v", got, tt.want)
			}
		})
	}
}
