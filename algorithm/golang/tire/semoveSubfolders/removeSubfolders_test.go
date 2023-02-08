package semoveSubfolders

import (
	"reflect"
	"testing"
)

func Test_removeSubfolders(t *testing.T) {
	type args struct {
		folder []string
	}
	tests := []struct {
		name string
		args args
		want []string
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}}, []string{"/a", "/c/d", "/c/f"}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := removeSubfolders(tt.args.folder); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("removeSubfolders() = %v, want %v", got, tt.want)
			}
		})
	}
}
