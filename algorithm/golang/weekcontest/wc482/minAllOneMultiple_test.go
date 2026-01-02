package wc482

import "testing"

func Test_minAllOneMultiple(t *testing.T) {
	type args struct {
		k int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{7}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minAllOneMultiple(tt.args.k); got != tt.want {
				t.Errorf("minAllOneMultiple() = %v, want %v", got, tt.want)
			}
		})
	}
}
