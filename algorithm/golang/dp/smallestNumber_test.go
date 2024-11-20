package dp

import "testing"

func Test_smallestNumber(t *testing.T) {
	type args struct {
		num string
		t   int64
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		{"t1", args{"10", 320}, "588"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := smallestNumber(tt.args.num, tt.args.t); got != tt.want {
				t.Errorf("smallestNumber() = %v, want %v", got, tt.want)
			}
		})
	}
}
