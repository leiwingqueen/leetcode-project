package math

import (
	"reflect"
	"testing"
)

func Test_powerfulIntegers(t *testing.T) {
	type args struct {
		x     int
		y     int
		bound int
	}
	tests := []struct {
		name string
		args args
		want []int
	}{
		// TODO: Add test cases.
		{"t1", args{2, 3, 10}, []int{2, 3, 4, 5, 7, 9, 10}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := powerfulIntegers(tt.args.x, tt.args.y, tt.args.bound); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("powerfulIntegers() = %v, want %v", got, tt.want)
			}
		})
	}
}
