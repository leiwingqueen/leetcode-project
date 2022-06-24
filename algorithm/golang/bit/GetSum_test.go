package bit

import "testing"

func Test_getSum(t *testing.T) {
	type args struct {
		a int
		b int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{1, 1}, 2},
		{"t2", args{1, 2}, 3},
		{"t3", args{1, -1}, 0},
		{"t4", args{3, -1}, 2},
		{"t5", args{3, -4}, -1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := getSum(tt.args.a, tt.args.b); got != tt.want {
				t.Errorf("getSum() = %v, want %v", got, tt.want)
			}
		})
	}
}
