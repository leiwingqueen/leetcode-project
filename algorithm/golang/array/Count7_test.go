package array

import "testing"

func Test_count7(t *testing.T) {
	type args struct {
		max int
	}
	tests := []struct {
		name string
		args args
	}{
		// TODO: Add test cases.
		{"first test", args{100}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			count7(tt.args.max)
		})
	}
}
