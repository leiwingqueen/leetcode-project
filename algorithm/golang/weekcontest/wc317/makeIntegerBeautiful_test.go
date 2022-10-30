package wc317

import "testing"

func Test_makeIntegerBeautiful(t *testing.T) {
	type args struct {
		n      int64
		target int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{467, 6}, 33},
		{"t2", args{110, 1}, 890},
		//94598
		//6
		{"t3", args{94598, 6}, 5402},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := makeIntegerBeautiful(tt.args.n, tt.args.target); got != tt.want {
				t.Errorf("makeIntegerBeautiful() = %v, want %v", got, tt.want)
			}
		})
	}
}
