package wc354

import "testing"

func Test_longestValidSubstring2(t *testing.T) {
	type args struct {
		word      string
		forbidden []string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// "cbaaaabc"
		//["aaa","cb"]
		{"t1", args{"cbaaaabc", []string{"aaa", "cb"}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := longestValidSubstring2(tt.args.word, tt.args.forbidden); got != tt.want {
				t.Errorf("longestValidSubstring2() = %v, want %v", got, tt.want)
			}
		})
	}
}
