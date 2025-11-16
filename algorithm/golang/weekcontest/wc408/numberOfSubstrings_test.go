package wc408

import "testing"

func Test_numberOfSubstrings2(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"101101"}, 16},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numberOfSubstrings3(tt.args.s); got != tt.want {
				t.Errorf("numberOfSubstrings2() = %v, want %v", got, tt.want)
			}
		})
	}
}
