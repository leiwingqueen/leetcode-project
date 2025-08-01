package wc460

import "testing"

func Test_numOfSubsequences(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{"LMCT"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numOfSubsequences(tt.args.s); got != tt.want {
				t.Errorf("numOfSubsequences() = %v, want %v", got, tt.want)
			}
		})
	}
}
