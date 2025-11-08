package dp

import "testing"

func Test_minimumOneBitOperations(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{3}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumOneBitOperations2(tt.args.n); got != tt.want {
				t.Errorf("minimumOneBitOperations() = %v, want %v", got, tt.want)
			}
		})
	}
}
