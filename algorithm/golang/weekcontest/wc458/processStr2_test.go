package wc458

import "testing"

func Test_processStr2(t *testing.T) {
	type args struct {
		s string
		k int64
	}
	tests := []struct {
		name string
		args args
		want byte
	}{
		// TODO: Add test cases.
		{"t1", args{"a#b%*", 1}, 'a'},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := processStr2(tt.args.s, tt.args.k); got != tt.want {
				t.Errorf("processStr2() = %v, want %v", got, tt.want)
			}
		})
	}
}
