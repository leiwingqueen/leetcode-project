package wc356

import "testing"

func Test_minimumString(t *testing.T) {
	type args struct {
		a string
		b string
		c string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		// "abc"
		//"bca"
		//"aaa"
		{"t1", args{"abc", "bca", "aaa"}, "aaabca"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumString(tt.args.a, tt.args.b, tt.args.c); got != tt.want {
				t.Errorf("minimumString() = %v, want %v", got, tt.want)
			}
		})
	}
}
