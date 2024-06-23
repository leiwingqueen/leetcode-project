package greedy

import "testing"

func Test_smallestBeautifulString(t *testing.T) {
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
		{"t1", args{"abcz", 26}, "abda"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := smallestBeautifulString(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("smallestBeautifulString() = %v, want %v", got, tt.want)
			}
		})
	}
}
