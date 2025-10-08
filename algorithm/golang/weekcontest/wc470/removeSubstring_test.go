package wc470

import "testing"

func Test_removeSubstring(t *testing.T) {
	type args struct {
		s string
		k int
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		{"t1", args{"(())", 1}, ""},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := removeSubstring(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("removeSubstring() = %v, want %v", got, tt.want)
			}
		})
	}
}
