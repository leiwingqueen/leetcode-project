package wc300

import "testing"

func Test_peopleAwareOfSecret(t *testing.T) {
	type args struct {
		n      int
		delay  int
		forget int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{6, 2, 4}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := peopleAwareOfSecret(tt.args.n, tt.args.delay, tt.args.forget); got != tt.want {
				t.Errorf("peopleAwareOfSecret() = %v, want %v", got, tt.want)
			}
		})
	}
}
