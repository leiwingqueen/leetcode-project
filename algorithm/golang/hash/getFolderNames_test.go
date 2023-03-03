package hash

import (
	"reflect"
	"testing"
)

func Test_getFolderNames(t *testing.T) {
	type args struct {
		names []string
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{"gta", "gta(1)", "gta", "avalon"}},
			[]string{"gta", "gta(1)", "gta(2)", "avalon"}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := getFolderNames2(tt.args.names); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("getFolderNames() = %v, want %v", got, tt.want)
			}
		})
	}
}
