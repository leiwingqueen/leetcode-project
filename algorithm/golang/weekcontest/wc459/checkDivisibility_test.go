package wc459

import "testing"

func Test_checkDivisibility(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{8}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := checkDivisibility(tt.args.n); got != tt.want {
				t.Errorf("checkDivisibility() = %v, want %v", got, tt.want)
			}
		})
	}
}
