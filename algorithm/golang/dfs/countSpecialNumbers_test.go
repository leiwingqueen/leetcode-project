package dfs

import "testing"

func Test_countSpecialNumbers2(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{20}, 19},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countSpecialNumbers2(tt.args.n); got != tt.want {
				t.Errorf("countSpecialNumbers2() = %v, want %v", got, tt.want)
			}
		})
	}
}
