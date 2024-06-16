package wc402

import "testing"

func Test_maximumTotalDamage(t *testing.T) {
	type args struct {
		power []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 1, 3, 4}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maximumTotalDamage(tt.args.power); got != tt.want {
				t.Errorf("maximumTotalDamage() = %v, want %v", got, tt.want)
			}
		})
	}
}
