package wc483

import (
	"reflect"
	"testing"
)

func Test_wordSquares(t *testing.T) {
	type args struct {
		words []string
	}
	tests := []struct {
		name string
		args args
		want [][]string
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{"able", "area", "echo", "also"}}, [][]string{{"able", "area", "echo", "also"}, {"area", "able", "also", "echo"}}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := wordSquares(tt.args.words); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("wordSquares() = %v, want %v", got, tt.want)
			}
		})
	}
}
