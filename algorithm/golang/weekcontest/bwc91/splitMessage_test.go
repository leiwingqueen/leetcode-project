package bwc91

import (
	"reflect"
	"testing"
)

func Test_splitMessage(t *testing.T) {
	type args struct {
		message string
		limit   int
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		// TODO: Add test cases.
		{"t1", args{"this is really a very awesome message", 9}, []string{
			"thi<1/14>", "s i<2/14>", "s r<3/14>", "eal<4/14>", "ly <5/14>", "a v<6/14>", "ery<7/14>", " aw<8/14>", "eso<9/14>", "me<10/14>", " m<11/14>", "es<12/14>", "sa<13/14>", "ge<14/14>",
		}},
		// "short message"
		//15
		{"t2", args{"short message", 15}, []string{
			"short message<1/1>",
		}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := splitMessage(tt.args.message, tt.args.limit); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("splitMessage() = %v, want %v", got, tt.want)
			}
		})
	}
}
