package wc434

import (
	"reflect"
	"testing"
)

func Test_countMentions(t *testing.T) {
	type args struct {
		numberOfUsers int
		events        [][]string
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		// 2
		//[["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]
		{"t1", args{2, [][]string{
			{"MESSAGE", "10", "id1 id0"},
			{"OFFLINE", "11", "0"},
			{"MESSAGE", "71", "HERE"},
		}}, []int{2, 2}},
		// 3
		//[["MESSAGE","2","HERE"],["OFFLINE","2","1"],["OFFLINE","1","0"],["MESSAGE","61","HERE"]]
		{"t2", args{3, [][]string{
			{"MESSAGE", "2", "HERE"},
			{"OFFLINE", "2", "1"},
			{"OFFLINE", "1", "0"},
			{"MESSAGE", "61", "HERE"},
		}}, []int{1, 0, 2}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countMentions(tt.args.numberOfUsers, tt.args.events); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("countMentions() = %v, want %v", got, tt.want)
			}
		})
	}
}
