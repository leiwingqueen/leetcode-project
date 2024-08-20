package dp

import "testing"

func Test_waysToReachStair(t *testing.T) {
	type args struct {
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{0}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := waysToReachStair2(tt.args.k); got != tt.want {
				t.Errorf("waysToReachStair() = %v, want %v", got, tt.want)
			}
		})
	}
}
