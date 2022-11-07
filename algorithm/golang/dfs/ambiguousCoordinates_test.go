package dfs

import (
	"reflect"
	"testing"
)

func Test_ambiguousCoordinates(t *testing.T) {
	type args struct {
		s string
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		// TODO: Add test cases.
		{"t1", args{"(123)"}, []string{"(1, 2.3)", "(1, 23)", "(1.2, 3)", "(12, 3)"}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := ambiguousCoordinates(tt.args.s); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("ambiguousCoordinates() = %v, want %v", got, tt.want)
			}
		})
	}
}
