package wc423

import "testing"

func Test_countKReducibleNumbers(t *testing.T) {
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
		{"t1", args{"1000", 2}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countKReducibleNumbers(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("countKReducibleNumbers() = %v, want %v", got, tt.want)
			}
		})
	}
}
