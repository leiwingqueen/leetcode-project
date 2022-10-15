package dp

import "testing"

func Test_distinctSubseqII(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abc"}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := distinctSubseqII(tt.args.s); got != tt.want {
				t.Errorf("distinctSubseqII() = %v, want %v", got, tt.want)
			}
		})
	}
}
