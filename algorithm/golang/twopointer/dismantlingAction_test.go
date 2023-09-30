package twopointer

import "testing"

func Test_dismantlingAction(t *testing.T) {
	type args struct {
		arr string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"dbascDdad"}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := dismantlingAction(tt.args.arr); got != tt.want {
				t.Errorf("dismantlingAction() = %v, want %v", got, tt.want)
			}
		})
	}
}
