package backtrace

import (
	"testing"
)

func Test_permutation(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		{"test1", args{"abc"}, []string{"abc", "acb", "bac", "bca", "cab", "cba"}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := permutation(tt.args.s); !match(tt.want, got) {
				t.Errorf("permutation() = %v, want %v", got, tt.want)
			}
		})
	}
}

func match(expect []string, actual []string) bool {
	if len(expect) != len(actual) {
		return false
	}
	mp := make(map[string]bool)
	for _, s := range expect {
		mp[s] = true
	}
	for _, s := range actual {
		if _, ok := mp[s]; !ok {
			return false
		}
	}
	return true
}
