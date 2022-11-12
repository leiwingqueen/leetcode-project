package dp

import "testing"

func Test_numTilings(t *testing.T) {
	type args struct {
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{3}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numTilings(tt.args.n); got != tt.want {
				t.Errorf("numTilings() = %v, want %v", got, tt.want)
			}
		})
	}
}
