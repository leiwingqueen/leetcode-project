package wc314

import "testing"

func Test_robotWithString(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		{"t1", args{"bydizfve"}, "bdevfziy"},
		{"t2", args{"zza"}, "azz"},
		{"t3", args{"vzhofnpo"}, "fnohopzv"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := robotWithString2(tt.args.s); got != tt.want {
				t.Errorf("robotWithString() = %v, want %v", got, tt.want)
			}
		})
	}
}
