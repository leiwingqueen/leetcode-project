package dp

import "testing"

func Test_numOfWays(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"first", args{1}, 12},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numOfWays(tt.args.n); got != tt.want {
				t.Errorf("numOfWays() = %v, want %v", got, tt.want)
			}
		})
	}
}
