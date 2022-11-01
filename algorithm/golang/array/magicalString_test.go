package array

import "testing"

func Test_magicalString(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{5}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := magicalString(tt.args.n); got != tt.want {
				t.Errorf("magicalString() = %v, want %v", got, tt.want)
			}
		})
	}
}
