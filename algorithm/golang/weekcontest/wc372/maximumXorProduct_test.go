package wc372

import "testing"

func Test_maximumXorProduct(t *testing.T) {
	type args struct {
		a int64
		b int64
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{12, 5, 4}, 98},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumXorProduct(tt.args.a, tt.args.b, tt.args.n); got != tt.want {
				t.Errorf("maximumXorProduct() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_maximumXorProduct2(t *testing.T) {
	type args struct {
		a int64
		b int64
		n int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{12, 5, 4}, 98},
		{"t2", args{6, 7, 5}, 930},
		{"t3", args{1, 6, 3}, 12},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumXorProduct2(tt.args.a, tt.args.b, tt.args.n); got != tt.want {
				t.Errorf("maximumXorProduct2() = %v, want %v", got, tt.want)
			}
		})
	}
}
