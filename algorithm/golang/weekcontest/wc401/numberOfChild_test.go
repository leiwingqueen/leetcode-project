package wc401

import "testing"

func Test_numberOfChild(t *testing.T) {
	type args struct {
		n int
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{3, 5}, 1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := numberOfChild(tt.args.n, tt.args.k); got != tt.want {
				t.Errorf("numberOfChild() = %v, want %v", got, tt.want)
			}
		})
	}
}
