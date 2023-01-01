package wc326

import "testing"

func Test_minimumPartition(t *testing.T) {
	type args struct {
		s string
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"165462", 60}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumPartition(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("minimumPartition() = %v, want %v", got, tt.want)
			}
		})
	}
}
