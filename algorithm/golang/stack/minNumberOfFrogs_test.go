package stack

import "testing"

func Test_minNumberOfFrogs(t *testing.T) {
	type args struct {
		croakOfFrogs string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"croakcroak"}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minNumberOfFrogs(tt.args.croakOfFrogs); got != tt.want {
				t.Errorf("minNumberOfFrogs() = %v, want %v", got, tt.want)
			}
		})
	}
}
