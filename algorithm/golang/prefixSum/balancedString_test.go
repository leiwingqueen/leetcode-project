package prefixSum

import "testing"

func Test_balancedString(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"QQWE"}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := balancedString(tt.args.s); got != tt.want {
				t.Errorf("balancedString() = %v, want %v", got, tt.want)
			}
		})
	}
}
