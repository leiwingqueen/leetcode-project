package bwc115

import (
	"reflect"
	"testing"
)

func Test_getWordsInLongestSubsequence2(t *testing.T) {
	type args struct {
		n      int
		words  []string
		groups []int
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		// TODO: Add test cases.
		{"t1", args{3, []string{"bab", "dab", "cab"}, []int{1, 2, 2}}, []string{"bab", "cab"}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := getWordsInLongestSubsequence2(tt.args.n, tt.args.words, tt.args.groups); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("getWordsInLongestSubsequence2() = %v, want %v", got, tt.want)
			}
		})
	}
}
