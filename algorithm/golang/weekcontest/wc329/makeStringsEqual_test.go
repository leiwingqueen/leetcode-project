package wc329

import "testing"

func Test_makeStringsEqual(t *testing.T) {
	type args struct {
		s      string
		target string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{"11", "00"}, false},
		// "001000"
		//"000100"
		{"t2", args{"001000", "000100"}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := makeStringsEqual(tt.args.s, tt.args.target); got != tt.want {
				t.Errorf("makeStringsEqual() = %v, want %v", got, tt.want)
			}
		})
	}
}
