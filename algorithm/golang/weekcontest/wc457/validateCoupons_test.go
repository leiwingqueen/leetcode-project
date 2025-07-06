package wc457

import (
	"reflect"
	"testing"
)

func Test_validateCoupons(t *testing.T) {
	type args struct {
		code         []string
		businessLine []string
		isActive     []bool
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{
			"SAVE20", "", "PHARMA5", "SAVE@20",
		}, []string{
			"restaurant", "grocery", "pharmacy", "restaurant",
		}, []bool{
			true, true, true, true,
		}}, []string{"PHARMA5", "SAVE20"}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := validateCoupons(tt.args.code, tt.args.businessLine, tt.args.isActive); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("validateCoupons() = %v, want %v", got, tt.want)
			}
		})
	}
}
