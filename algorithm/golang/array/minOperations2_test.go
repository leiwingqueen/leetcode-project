package array

import (
	"reflect"
	"testing"
)

func Test_minOperations3(t *testing.T) {
	type args struct {
		boxes string
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{"110"}, []int{1, 1, 3}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minOperations3(tt.args.boxes); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("minOperations3() = %v, want %v", got, tt.want)
			}
		})
	}
}
