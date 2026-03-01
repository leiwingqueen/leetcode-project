package wc490

import "testing"

func Test_isDigitorialPermutation(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{40558}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := isDigitorialPermutation(tt.args.n); got != tt.want {
				t.Errorf("isDigitorialPermutation() = %v, want %v", got, tt.want)
			}
		})
	}
}
