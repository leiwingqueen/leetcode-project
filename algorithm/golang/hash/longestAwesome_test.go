package hash

import "testing"

func Test_longestAwesome(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{"3242415"}, 5},
		{"t2", args{"185801630663498"}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := longestAwesome3(tt.args.s); got != tt.want {
				t.Errorf("longestAwesome() = %v, want %v", got, tt.want)
			}
		})
	}
}
