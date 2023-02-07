package slidewindow

import (
	"reflect"
	"testing"
)

func Test_alertNames(t *testing.T) {
	type args struct {
		keyName []string
		keyTime []string
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		// ["john","john","john"]
		//["23:58","23:59","00:01"]
		{"t1", args{[]string{"john", "john", "john"},
			[]string{"23:58", "23:59", "00:01"}}, []string{}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := alertNames(tt.args.keyName, tt.args.keyTime); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("alertNames() = %v, want %v", got, tt.want)
			}
		})
	}
}
