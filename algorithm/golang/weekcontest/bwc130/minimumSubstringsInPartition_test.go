package bwc130

import "testing"

func Test_minimumSubstringsInPartition(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"fabccddg"}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumSubstringsInPartition(tt.args.s); got != tt.want {
				t.Errorf("minimumSubstringsInPartition() = %v, want %v", got, tt.want)
			}
		})
	}
}
