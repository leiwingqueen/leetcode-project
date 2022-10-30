package wc317

import (
	"reflect"
	"testing"
)

func Test_mostPopularCreator(t *testing.T) {
	type args struct {
		creators []string
		ids      []string
		views    []int
	}
	tests := []struct {
		name string
		args args
		want [][]string
	}{
		// TODO: Add test cases.
		// creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
		{"t1", args{[]string{"alice", "alice", "alice"}, []string{"a", "b", "c"}, []int{1, 2, 2}}, [][]string{
			{"alice", "b"},
		}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := mostPopularCreator(tt.args.creators, tt.args.ids, tt.args.views); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("mostPopularCreator() = %v, want %v", got, tt.want)
			}
		})
	}
}
