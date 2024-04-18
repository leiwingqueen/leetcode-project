package greedy

import (
	"reflect"
	"testing"
)

func Test_findOriginalArray(t *testing.T) {
	type args struct {
		changed []int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		{"t1", args{[]int{1, 3, 4, 2, 6, 8}}, []int{1, 3, 4}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findOriginalArray(tt.args.changed); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("findOriginalArray() = %v, want %v", got, tt.want)
			}
		})
	}
}
