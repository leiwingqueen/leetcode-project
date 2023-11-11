package bwc117

import "testing"

func Test_stringCount(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{10}, 83943898},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := stringCount(tt.args.n); got != tt.want {
				t.Errorf("stringCount() = %v, want %v", got, tt.want)
			}
		})
	}
}
