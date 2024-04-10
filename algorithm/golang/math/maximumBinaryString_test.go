package math

import "testing"

func Test_maximumBinaryString(t *testing.T) {
	type args struct {
		binary string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		{"t1", args{"000110"}, "111011"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumBinaryString(tt.args.binary); got != tt.want {
				t.Errorf("maximumBinaryString() = %v, want %v", got, tt.want)
			}
		})
	}
}
