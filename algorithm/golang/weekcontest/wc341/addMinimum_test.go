package wc341

import "testing"

func Test_addMinimum(t *testing.T) {
	type args struct {
		word string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"b"}, 2},
		{"t2", args{"aaa"}, 6},
		{"t1", args{"abc"}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := addMinimum2(tt.args.word); got != tt.want {
				t.Errorf("addMinimum() = %v, want %v", got, tt.want)
			}
		})
	}
}
