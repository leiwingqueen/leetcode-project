package wc311

import (
	"reflect"
	"testing"
)

func Test_sumPrefixScores(t *testing.T) {
	type args struct {
		words []string
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{"abc", "ab", "bc", "b"}}, []int{5, 4, 3, 2}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sumPrefixScores(tt.args.words); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("sumPrefixScores() = %v, want %v", got, tt.want)
			}
		})
	}
}
