package bwc98

import "testing"

func Test_minMaxDifference(t *testing.T) {
	type args struct {
		num int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{11891}, 99009},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minMaxDifference(tt.args.num); got != tt.want {
				t.Errorf("minMaxDifference() = %v, want %v", got, tt.want)
			}
		})
	}
}
