package slidewindow

import "testing"

func Test_lengthOfLongestSubstringKDistinct(t *testing.T) {
	type args struct {
		s string
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t3", args{"cacaaaabbbacaabccbcc", 2}, 8},
		{"t1", args{"eceba", 2}, 3},
		{"t2", args{"aa", 0}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := lengthOfLongestSubstringKDistinct(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("lengthOfLongestSubstringKDistinct() = %v, want %v", got, tt.want)
			}
		})
	}
}
