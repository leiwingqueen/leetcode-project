package bwc129

import "testing"

func Test_numberOfStableArrays2(t *testing.T) {
	type args struct {
		zero  int
		one   int
		limit int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{1, 1, 2}, 2},
		{"t2", args{1, 2, 1}, 1},
		{"t3", args{3, 3, 2}, 14},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numberOfStableArrays2(tt.args.zero, tt.args.one, tt.args.limit); got != tt.want {
				t.Errorf("numberOfStableArrays2() = %v, want %v", got, tt.want)
			}
		})
	}
}
